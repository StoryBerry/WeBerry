from google.cloud import storage
import os
import numpy as np
from urllib import request as req
from tempfile import TemporaryFile
from PIL import Image
import io
import cv2
from datetime import datetime

os.environ["GOOGLE_APPLICATION_CREDENTIALS"] = "C:\\Users\\Will.Lee\\Downloads\\gcspr\\gcspr\\src\\main\\resources\\weberry-372406-837b8f43fdbe.json"

bucket_name = "weberry-storage"
base_url = "https://storage.googleapis.com/weberry-storage"
mDate = datetime.now().strftime('%y.%m.%d')

storage_client = storage.Client()
bucket = storage_client.bucket(bucket_name)

def upload_image(image, farmId, idx, status):
  file_name = f'disease/{mDate}/{farmId}/{idx}_{status}.jpg'
  with TemporaryFile() as temp:
    image = np.array(image)
    image.tofile(temp)
    blob = bucket.blob(file_name)
    blob.upload_from_file(temp)
  url = f'{base_url}/{file_name}'

  return url

def read_image(farmId):
  prefix = f'farm/{mDate}/{farmId}'
  blobs = bucket.list_blobs(prefix=prefix)
  images = []
  urls = []
  for blob in blobs:
    image_array = np.asarray(bytearray(blob.download_as_bytes()), dtype="uint8")
    images.append(image_array)
    urls.append(f'{base_url}/{blob.name}')

  return images, urls