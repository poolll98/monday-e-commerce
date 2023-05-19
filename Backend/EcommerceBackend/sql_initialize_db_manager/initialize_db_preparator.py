
sql_raw_instruction = "sql_raw_instructions.txt"
categories_raw_db = "categories_raw_db.txt"
initialize_db = "initialize_db.sql"
sql_insertion_instruction = "INSERT INTO product_category(id,category_name) VALUES(counter,'xxxx');"
sql_alter_instruction = "ALTER SEQUENCE product_category_id_seq RESTART WITH counter;"

if __name__ == "__main__":
    with open(sql_raw_instruction, 'r') as f:
        raw_instructions = f.read()

    categories = []
    with open(categories_raw_db, 'r') as f:
        for line in f:
            categories.append(line.rstrip())

    categories = sorted([category.lower() for category in list(set(categories))])
    sql_instructions = ""

    for n,category in enumerate(categories):
        sql_instructions += sql_insertion_instruction.replace("xxxx",category).replace("counter", str(n)) +"\n"
        if(category=='food'):
            raw_instructions = raw_instructions.replace("*_food_id_*", str(n))


    sql_instructions += sql_alter_instruction.replace("counter", str(len(list(set(categories)))))
    raw_instructions = raw_instructions.replace("XXXX", sql_instructions)

    with open(initialize_db, 'w') as f:
        f.write(raw_instructions)

    print("initialize_db.sql prepared !")
