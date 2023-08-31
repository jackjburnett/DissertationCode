from rest_framework import serializers
from .models import Appusage
from .models import Gpslog
from .models import Msreports
from .models import Userdetails

class AppUsageSerializer(serializers.ModelSerializer):
    class Meta:
        model = Appusage
        fields = ['usageid', 'packagename', 'appname', 'timeopened', 'timeclosed', 'userid']

class GpslogSerializer(serializers.ModelSerializer):
    class Meta:
        model = Gpslog
        fields = ['time', 'latitude', 'longitude', 'userid', 'gpslogid']

class ReportsSerializer(serializers.ModelSerializer):
    class Meta:
        model = Msreports
        fields = ['q1', 'q2', 'q3', 'q4', 'q5', 'q6', 'q7', 'q8', 'q9', 'q10', 'q11', 'q12', 'q13', 'q14', 'q15', 'q16', 'q17', 'q18', 'time', 'reportid', 'userid']

class UsersSerializer(serializers.ModelSerializer):
    class Meta:
        model = Userdetails
        fields = ['appkey', 'userid']