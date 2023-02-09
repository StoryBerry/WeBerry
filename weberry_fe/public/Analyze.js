const disease = new Map();
disease.set("AngularLeafspot", "세균모무늬병");
disease.set("AnthracnoseFruitRot", "탄저병");
disease.set("BlossomBlight", "역병");
disease.set("GrayMold", "쟃빛곰팡이병");
disease.set("LeafSpot", "흰무늬병");
disease.set("PowderyMildewFruit", "흰가루병");
disease.set("PowderyMildewLeaf", "흰가루병");

export default function analyze_status(status) {
  return disease.has(status)
    ? `"${disease.get(status)}"이 의심됩니다.`
    : "정상적으로 잘 자라고 있습니다.";
}
