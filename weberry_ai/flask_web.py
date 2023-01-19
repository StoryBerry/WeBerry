from flask import Flask, jsonify
from datetime import datetime
from pathlib import Path
import os
import cv2
import numpy as np

app = Flask(__name__)

# 상수들
INPUT_WIDTH = 640
INPUT_HEIGHT = 640
SCORE_THRESHOLD = 0.5
NMS_THRESHOLD = 0.45
CONFIDENCE_THRESHOLD = 0.45

# 폰트들
FONT_FACE = cv2.FONT_HERSHEY_SIMPLEX
FONT_SCALE = 0.7
THICKNESS = 1

# 색깔지정
BLACK  = (0,0,0)
BLUE   = (255,178,50)
YELLOW = (0,255,255)
RED = (0,0,255)

def draw_label(input_image, label, left, top):
	"""Draw text onto image at location."""
	
	# 텍스트 사이즈 조정
	text_size = cv2.getTextSize(label, FONT_FACE, FONT_SCALE, THICKNESS)
	dim, baseline = text_size[0], text_size[1]
	# 텍스트를 둘러싼 검은 상자. 
	cv2.rectangle(input_image, (left, top), (left + dim[0], top + dim[1] + baseline), BLACK, cv2.FILLED);
	# 내부에 텍스트 사용.
	cv2.putText(input_image, label, (left, top + dim[1]), FONT_FACE, FONT_SCALE, YELLOW, THICKNESS, cv2.LINE_AA)

	return input_image

# OpenCV의 dnn 모듈이 처리할 수 있는 이미지 Blob에 
# 학습된 모델의 조건을 기준으로 프레임별로 너비, 높이, 0~255 사이 레벨 조정, BGR 평균 넣기  
def pre_process(input_image, net):
	# 4D BLOB화
	blob = cv2.dnn.blobFromImage(input_image, 1/255, (INPUT_WIDTH, INPUT_HEIGHT), [0,0,0], 1, crop=False)

	# DNN에 프레임 넣기
	net.setInput(blob)

	# 순방향 네트워크 실행
	output_layers = net.getUnconnectedOutLayersNames()
 
	# 전체 layer에서 실제 detection이 완료된 레이어를 출력
	outputs = net.forward(output_layers)

	return outputs

# 프레임별로 net에서 출력된 후 추론한 id, 신뢰도, 박스 영역을 별도의 리스트로 저장
def post_process(input_image, outputs):
	# Lists to hold respective values while unwrapping.
	class_ids = []
	confidences = []
	boxes = []

	rows = outputs[0].shape[1]
	image_height, image_width = input_image.shape[:2]

	# 이미지 resize 
	x_factor = image_width / INPUT_WIDTH
	y_factor =  image_height / INPUT_HEIGHT

	# 하나의 행씩 값을 받음. 전체 85 중 앞의 4개는 바운딩박스의 좌표 관련 값
	# 그 다음은 objectness Score, 나머지 80개는 Class Scores(80개 클래스에 대한 각각의 확률값)
	for r in range(rows):
		row = outputs[0][0][r]
		confidence = row[4] 

		# Confidence가 우리가 정한 CONFIDENCE_THRESHOLD보다 높으면 검출하기로 결정
		if confidence >= CONFIDENCE_THRESHOLD:
			classes_scores = row[5:] 

			# class score가 가장 높은 좌표의 인덱스번호 찾기
			class_id = np.argmax(classes_scores)

			#  해당 score가 우리가 정한 score threshold보다 높으면 경계 출력
			if (classes_scores[class_id] > SCORE_THRESHOLD):
				confidences.append(confidence)
				class_ids.append(class_id)

				cx, cy, w, h = row[0], row[1], row[2], row[3]

				left = int((cx - w/2) * x_factor)
				top = int((cy - h/2) * y_factor)
				width = int(w * x_factor)
				height = int(h * y_factor)
			  
				box = np.array([left, top, width, height])
				boxes.append(box)

	# NMS - non maximum suppression: 박스 여러개가 겹칠 경우 가장 높은 confidence를 가진 하나만을 검출
	indices = cv2.dnn.NMSBoxes(boxes, confidences, CONFIDENCE_THRESHOLD, NMS_THRESHOLD)
	for i in indices:
		box = boxes[i]
		left = box[0]
		top = box[1]
		width = box[2]
		height = box[3]
		cv2.rectangle(input_image, (left, top), (left + width, top + height), BLUE, 3*THICKNESS)
		label = "{}:{:.2f}".format(classes[class_ids[i]], confidences[i])
		output_image = draw_label(input_image, label, left, top)
	
	return locals().get('output_image', None), class_ids

# 학습시킨 라벨별 클래스명
classesFile = "coco.names"
classes = None
with open(classesFile, 'rt') as f:
    classes = f.read().rstrip('\n').split('\n')

# 학습시켰던 ONNX 파일을 모델에 넣기
modelWeights = "strawberrybest.onnx"
net = cv2.dnn.readNet(modelWeights)


@app.route('/analyze/<farmId>')
def analyze_image(farmId):
	mDate = datetime.now().strftime('%y.%m.%d')
	date = datetime.now().strftime('%y%m%d')
	path = f'/home/weberry/Desktop/images/farm/{mDate}/{farmId}/'
	output_path = f'/home/weberry/Desktop/images/disease/{mDate}/{farmId}'
	
	if not Path(output_path).exists():
		os.makedirs(output_path)
	images = [path + fileName for fileName in os.listdir(path)]
	
	reports = {'requestList': []}

	for idx, image in enumerate(images):
		decodedImage = np.fromfile(image, np.uint8)
		img = cv2.imdecode(decodedImage, cv2.IMREAD_COLOR)
		detections = pre_process(img, net)
		output_img, results = post_process(img, detections)
		
		if output_img is not None:
			status = classes[results[0]].replace(" ", "")
			analayzedImageUrl = f'{output_path}/{status}_{idx + 1}.jpg'
			result, encoded_img = cv2.imencode('.jpg', output_img)
 
			if result:
					with open(analayzedImageUrl, mode='wb') as f:
							encoded_img.tofile(f)

		report = {'status': locals().get('status', 'Normal'),
							'baseImageUrl': image,
							'analyzedImageUrl': locals().get('analayzedImageUrl', None),
							'data': {'id': f'{farmId}_{date}_{idx + 1}'}}
		reports['requestList'].append(report)
	
	return jsonify(reports)

if __name__ == '__main__':
	app.run(host='0.0.0.0', port='8000', debug=True)