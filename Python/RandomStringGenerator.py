import random
import string
import csv

###### RandomString Generator #########

#Write col1 length
word_length_1 = 10
total_word_1 = 100
col1_id = "id"


#Write col2 length
word_length_2 = 10
total_word_2 = 100
col2_id = "password"

#######################################

words_1 = []
words_2 = []

strigPool = string.ascii_letters + string.digits # ex) : ABbcd...12123
passwordPool = string.punctuation + strigPool # ex) : $*$(@#$*) .... ABbcd...123


# Make Random Id
for i in range(total_word_1):
    result = ""
    for j in range(word_length_1):
        result += random.choice(strigPool)
    print(result)
    words_1.insert(i,result)

# Make Random Password
for i in range(total_word_2):
    result = ""
    for j in range(word_length_2):
        result += random.choice(passwordPool)
    words_2.insert(i,result)



# File save .csv
f = open("C:/Users/사용자이름/Desktop/스크립트/test_data.csv", 'w', newline='')

wr =csv.writer(f)

for i in range(total_word_1):
    wr.writerow([words_1[i], words_2[i]])

f.close()
