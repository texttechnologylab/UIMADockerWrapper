import xml.etree.ElementTree as ET
import sys
import os

def dump(iterator,counter):
    for text in iterator:
        counter+=1
        with open('output/'+str(counter)+'.txt','w') as f:
            if text.text != None:
                f.write(text.text)
    return counter



os.mkdir("output")
root = ET.parse('./AuswärtigesAmt.xml').getroot()
root2 = ET.parse('./Bundesregierung.xml').getroot()
root3 = ET.parse('./Bundespräsidenten.xml').getroot()
root4 = ET.parse('./Bundestagspräsidenten.xml').getroot()

counter = 0
counter = dump(root.iter("rohtext"),counter)
counter = dump(root2.iter("rohtext"),counter)
counter = dump(root3.iter("rohtext"),counter)
counter = dump(root4.iter("rohtext"),counter)


