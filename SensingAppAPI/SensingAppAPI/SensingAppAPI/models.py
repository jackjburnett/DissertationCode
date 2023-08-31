# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Appusage(models.Model):
    usageid = models.AutoField(primary_key=True)
    packagename = models.CharField(max_length=100)
    appname = models.CharField(max_length=50)
    timeopened = models.DateTimeField()
    timeclosed = models.DateTimeField()
    userid = models.ForeignKey('Userdetails', models.DO_NOTHING, db_column='userid')

    class Meta:
        managed = False
        db_table = 'appusage'


class AuthGroup(models.Model):
    name = models.CharField(unique=True, max_length=150)

    class Meta:
        managed = False
        db_table = 'auth_group'


class AuthGroupPermissions(models.Model):
    id = models.BigAutoField(primary_key=True)
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)
    permission = models.ForeignKey('AuthPermission', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_group_permissions'
        unique_together = (('group', 'permission'),)


class AuthPermission(models.Model):
    name = models.CharField(max_length=255)
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING)
    codename = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'auth_permission'
        unique_together = (('content_type', 'codename'),)


class AuthUser(models.Model):
    password = models.CharField(max_length=128)
    last_login = models.DateTimeField(blank=True, null=True)
    is_superuser = models.BooleanField()
    username = models.CharField(unique=True, max_length=150)
    first_name = models.CharField(max_length=150)
    last_name = models.CharField(max_length=150)
    email = models.CharField(max_length=254)
    is_staff = models.BooleanField()
    is_active = models.BooleanField()
    date_joined = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'auth_user'


class AuthUserGroups(models.Model):
    id = models.BigAutoField(primary_key=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_groups'
        unique_together = (('user', 'group'),)


class AuthUserUserPermissions(models.Model):
    id = models.BigAutoField(primary_key=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    permission = models.ForeignKey(AuthPermission, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_user_permissions'
        unique_together = (('user', 'permission'),)


class DjangoAdminLog(models.Model):
    action_time = models.DateTimeField()
    object_id = models.TextField(blank=True, null=True)
    object_repr = models.CharField(max_length=200)
    action_flag = models.SmallIntegerField()
    change_message = models.TextField()
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING, blank=True, null=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'django_admin_log'


class DjangoContentType(models.Model):
    app_label = models.CharField(max_length=100)
    model = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'django_content_type'
        unique_together = (('app_label', 'model'),)


class DjangoMigrations(models.Model):
    id = models.BigAutoField(primary_key=True)
    app = models.CharField(max_length=255)
    name = models.CharField(max_length=255)
    applied = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_migrations'


class DjangoSession(models.Model):
    session_key = models.CharField(primary_key=True, max_length=40)
    session_data = models.TextField()
    expire_date = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_session'


class Gpslog(models.Model):
    gpslogid = models.AutoField(primary_key=True)
    time = models.DateTimeField(db_column='Time')  # Field name made lowercase.
    longitude = models.DecimalField(max_digits=65535, decimal_places=8)
    latitude = models.DecimalField(max_digits=65535, decimal_places=8)
    userid = models.ForeignKey('Userdetails', models.DO_NOTHING, db_column='userid')

    class Meta:
        managed = False
        db_table = 'gpslog'


class Msreports(models.Model):
    reportid = models.AutoField(primary_key=True)
    userid = models.ForeignKey('Userdetails', models.DO_NOTHING, db_column='userid')
    time = models.DateTimeField(db_column='Time')  # Field name made lowercase.
    q1 = models.SmallIntegerField(blank=True, null=True)
    q2 = models.SmallIntegerField(blank=True, null=True)
    q3 = models.SmallIntegerField(blank=True, null=True)
    q4 = models.SmallIntegerField(blank=True, null=True)
    q5 = models.SmallIntegerField(blank=True, null=True)
    q6 = models.SmallIntegerField(blank=True, null=True)
    q7 = models.SmallIntegerField(blank=True, null=True)
    q8 = models.SmallIntegerField(blank=True, null=True)
    q9 = models.SmallIntegerField(blank=True, null=True)
    q10 = models.SmallIntegerField(blank=True, null=True)
    q11 = models.SmallIntegerField(blank=True, null=True)
    q12 = models.SmallIntegerField(blank=True, null=True)
    q13 = models.SmallIntegerField(blank=True, null=True)
    q14 = models.SmallIntegerField(blank=True, null=True)
    q15 = models.SmallIntegerField(blank=True, null=True)
    q16 = models.SmallIntegerField(blank=True, null=True)
    q17 = models.SmallIntegerField(blank=True, null=True)
    q18 = models.SmallIntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'msreports'


class Userdetails(models.Model):
    userid = models.AutoField(primary_key=True)
    appkey = models.CharField(max_length=256)

    class Meta:
        managed = False
        db_table = 'userdetails'
