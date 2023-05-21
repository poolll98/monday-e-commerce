from PIL import Image
import io
import psycopg2
from psycopg2 import Binary


def convert_image_to_byte_array(image_path):
    with open(image_path, 'rb') as file:
        image = Image.open(file)
        byte_array = io.BytesIO()
        image.save(byte_array, format='JPEG')
        byte_array = byte_array.getvalue()
        return byte_array


if __name__ == "__main__":
    # Connect to the PostgreSQL database
    connection = psycopg2.connect(database="postgres", user="postgres", password="pgpwd1", host="pg-db", port=5432)

    # Create a cursor object
    cursor = connection.cursor()
    for n in range(1,11):
        byte_image = convert_image_to_byte_array(f"images/item{n}.jpg")
        # Prepare the SQL statement with a placeholder for the binary data
        update_statement = "UPDATE product SET media=%s WHERE id=xxxx;"
        update_statement = update_statement.replace("xxxx", str(n))
        # Execute the statement with the binary data as a parameter
        cursor.execute(update_statement, (Binary(byte_image),))
        connection.commit()
        print(f"{n}: completed!")

    # Close the cursor and connection
    cursor.close()
    connection.close()