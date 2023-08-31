"""
WSGI config for SensingAppAPI project.

It exposes the WSGI callable as a module-level variable named ``application``.

For more information on this file, see
https://docs.djangoproject.com/en/4.0/howto/deployment/wsgi/
"""

path = '/home/jackjburnett/SensingAppAPI/'
if path not in sys.path:
    sys.path.append(path)

import os



from django.core.wsgi import get_wsgi_application

os.environ['DJANGO_SETTINGS_MODULE'] = 'SensingAppAPI.settings'

application = get_wsgi_application()
