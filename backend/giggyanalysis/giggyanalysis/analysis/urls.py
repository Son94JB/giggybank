from django.urls import path
from . import views

app_name = "analysis"
urlpatterns = [
    # path("", views.analysis),
    path("receive", views.receive),
]
