from django.shortcuts import render
import pandas as pd
import json
from django.http import JsonResponse
import fasttext
from django.views.decorators.csrf import csrf_exempt
from PyKomoran import *

# Create your views here.
def preprocessing(data):
    json_data = pd.json_normalize(data)
    # json_data["content"] = 
    return analysis(json_data)
def analysis(data):
    komoran = Komoran("EXP")
    komoran.set_user_dic("user_dic/dic.user")
    model = fasttext.load_model("fasttext_model/fasttext.bin")
    data["preprocess_content"] = data["content"].apply(lambda x: komoran.nouns(x))
    data["preprocess_content"] = " "+data["preprocess_content"].apply(lambda x: " ".join(x))
    data["category"] = data["preprocess_content"].apply(lambda x: model.predict(x)[0][0].replace("__label__",""))
    data["category"] = data["category"].apply(lambda x: x.replace(",",""))
    data = data.drop("preprocess_content", axis=1)
    analysis_data = data.to_dict(orient='records')
    return analysis_data
    
@csrf_exempt
def receive(request):
    if request.method == 'POST':
        history_data = json.loads(request.body)
        analysis_data = preprocessing(history_data)
        print(analysis_data)
        return JsonResponse({"status" : "success", "data" : analysis_data})
    
    else:
        return JsonResponse({"status" : "fail", "error" : "Only Post"})