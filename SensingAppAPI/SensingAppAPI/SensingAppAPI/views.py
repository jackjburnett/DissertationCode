from django.http import JsonResponse
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status

from .models import Appusage
from .models import Gpslog
from .models import Msreports
from .models import Userdetails

from .serializers import AppUsageSerializer
from .serializers import GpslogSerializer
from .serializers import ReportsSerializer
from .serializers import UsersSerializer

@api_view(['GET', 'POST'])
def usage_list(request, format=None):
    if request.method == 'GET':
        apps = Appusage.objects.all()
        serializer =  AppUsageSerializer(apps, many=True)
        return Response({'app_usage':serializer.data})
    if request.method == 'POST':
        serializer = AppUsageSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)

@api_view(['GET', 'POST'])
def gps_list(request, format=None):
    if request.method == 'GET':
        gps = Gpslog.objects.all()
        serializer = GpslogSerializer(gps, many=True)
        return Response({'gps_log': serializer.data})
    if request.method == 'POST':
        serializer = GpslogSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)



@api_view(['GET', 'POST'])
def report_list(request, format=None):
    if request.method == 'GET':
        reports = Msreports.objects.all()
        serializer = ReportsSerializer(reports, many=True)
        return Response({'reports': serializer.data})
    if request.method == 'POST':
        serializer = ReportsSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)

@api_view(['GET', 'POST'])
def user_list(request, format=None):
    if request.method == 'GET':
        users = Userdetails.objects.all()
        serializer = UsersSerializer(users, many=True)
        return Response({'users': serializer.data})
    if request.method == 'POST':
        serializer = UsersSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)

@api_view(['GET', 'PUT'])
def user_detail(request, id, format=None):
    try:
        user = Userdetails.objects.get(pk=id)
    except Userdetails.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)
    if request.method == 'GET':
        serializer = UsersSerializer(user)
        return Response(serializer.data)
    elif request.method == 'PUT':
        serializer = UsersSerializer(user, data=request.data)
        if serializer.is_valid():
            serializer.saver()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
