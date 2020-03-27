import random
import string
import csv

###### RandomString Generator #########

#Write col1 length
word_length_1 = 10
total_word_1 = 2000
col1_id = "email"


#Write col2 length
word_length_2 = 10
total_word_2 = 2000
col2_id = "password"


#Write col3 length
word_length_3 = 10
total_word_3 = 2000
col3_id = "nickname"

#######################################

words_1 = []
words_2 = []
words_3 = []

strigPool = string.ascii_letters + string.digits # ex) : ABbcd...12123
passwordPool = string.punctuation + strigPool # ex) : $*$(@#$*) .... ABbcd...123


# Make Random email
for i in range(total_word_1):
    result = ""
    for j in range(word_length_1):
        result += random.choice(strigPool)
    result += "@"
    for j in range(7):
        result += random.choice(strigPool)
    result += ".com"
    words_1.insert(i,result)

# Make Random Password
for i in range(total_word_2):
    result = ""
    for j in range(word_length_2):
        result += random.choice(passwordPool)
    words_2.insert(i,result)

# Make Random Nickname
for i in range(total_word_3):
    result = ""
    for j in range(word_length_3):
        result += random.choice(strigPool)
    words_3.insert(i,result)


# File save .csv
f = open("C:/Users/사용자아이디/Desktop/스크립트/test_data.csv", 'w', newline='')

wr =csv.writer(f)

for i in range(total_word_1):
    wr.writerow([words_1[i], words_2[i], words_3[i]])

f.close()
