package com.zhuzichu.nicehub

import org.junit.Test
import javax.xml.parsers.SAXParserFactory

class StringsParser {
    @Test
    fun main() {
        val path = "/Users/zhuzichu/AndroidStudioProjects/nice-kotlin/app/src/main/res/values-zh-rCN/strings.xml"
        val sAXParserFactory = SAXParserFactory.newInstance()
        val sAXParser = sAXParserFactory.newSAXParser()
        //需要编写处理类MySAXParserHandler
        val hander = StringsParserHandler()
        sAXParser.parse(path, hander)


        var doc =""
//        hander.wordList.forEach{
//            doc += (""" override var ${it.key}: String = "${it.value}"${"\n"} """)
//        }

//        hander.wordList.forEach {
//            doc += (""" abstract var ${it.key}: String${"\n"} """)
//        }

//        hander.wordList.forEach {
//            doc += (""" val ${it.key}: ObservableField<String> = ObservableField()${"\n"} """)
//        }

        hander.wordList.forEach {
            doc += (""" ${it.key}.set(font.${it.key})${"\n"} """)
        }
        print(doc)
    }
}