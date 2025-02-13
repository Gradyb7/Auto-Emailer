import pandas as pd
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from email.mime.image import MIMEImage
import smtplib
import os

# Your existing code to set up the email message
my_email = "gradyburns56@gmail.com"
password = "fqcp hswd kuds jldd"

# Create the email connection
connection = smtplib.SMTP("smtp.gmail.com", 587)
connection.starttls()
connection.login(user=my_email, password=password)

# Read the Excel file using pandas
df = pd.read_csv("./contacts.csv.csv")

for index, row in df.iterrows():
    name = row['name']
    email_1 = row['email_1']
    
    # Create a new message for each email
    message = MIMEMultipart()
    message["From"] = my_email
    message["To"] = email_1
    message["Subject"] = f"Web Design Services for {name}"
    
    # Format the text for each email
    text = """
    Hi there,
        
    I hope you’re doing well! My name is Grady Burns, and I’m a freshman at Kansas State University studying computer science. I’m looking to kickstart a web design business by helping local establishments like {name} stand out online.
        
    I noticed your current web presence and would love the opportunity to create a custom website that highlights everything unique about your business. For around $1,500, I’ll build the website and also provide ongoing support and feature updates ensuring that your site grows along with your business. Plus, there’s no risk—if you’re not fully satisfied with the final result, you won’t owe me anything.
        
    I’ve attached a few examples of my work for you to see. Please feel free to reach out with any questions or ideas. I’d be thrilled to work together to make {name} shine online!
        
    Best regards,
    Grady Burns
    816-825-4517
    """.format(name=name)
    
    message.attach(MIMEText(text, "plain"))
    
    # Attach images
    image1 = "./Example 1.png"
    image2 = "./image.png"
    with open(image1, "rb") as img:
        message.attach(MIMEImage(img.read(), name=os.path.basename(image1)))
    with open(image2, "rb") as img:
        message.attach(MIMEImage(img.read(), name=os.path.basename(image2)))

    ready = input("Ready to send? (y/n): ")
    if ready.lower() == "n":
        pass
    elif ready.lower() == "y":
        print(text)
        confirm = input("Send? (y/n): ")
        if confirm.lower() == "n":
            pass
        elif confirm.lower() == "y":
            try:
                connection.sendmail(from_addr=my_email, to_addrs=email_1, msg=message.as_string())
            except:
                print(f"Failed to send email to {name} at {email_1}")

connection.quit()