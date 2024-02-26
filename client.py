import requests
import json

# Read the file as bytes
with open("0C01_3500_inv.a2l", 'rb') as file:
    file_bytes = file.read()


content = list(file_bytes)
# Prepare the JSON data
json_data = {
    "name": "test_ignore",
    "a2lContent": content
}

# Make a POST request to the API with the JSON data
response = requests.post("http://127.0.0.1:8080/A2Ls/save-a2l", json=json_data)

# Check the response status
if response.status_code == 200:
    print("File sent successfully.")
else:
    print(f"Failed to send file. Status code: {response.status_code}")

