import requests

response = requests.get('https://jackjburnett.pythonanywhere.com/users')
print(response.json())