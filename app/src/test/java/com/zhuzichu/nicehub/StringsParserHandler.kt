package com.zhuzichu.nicehub

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class StringsParserHandler : DefaultHandler() {
    var word = Word()
    var wordList = ArrayList<Word>()
    var trim = ""

    override fun startDocument() {
        super.startDocument()
    }

    override fun endDocument() {
        super.endDocument()
    }

    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        super.startElement(uri, localName, qName, attributes)
        if (qName == "string") {
            word = Word()
            word.key = attributes?.getValue("name")
        }
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        super.endElement(uri, localName, qName)
        if(qName== "string"){
            word.value = trim
            wordList.add(word)
        }
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        super.characters(ch, start, length)
        val strings = String(ch!!, start, length)
        trim = strings.trim()
    }

    fun getList(): ArrayList<Word> {
        return wordList
    }
}