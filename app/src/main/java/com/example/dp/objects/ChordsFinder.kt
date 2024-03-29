package com.example.dp.objects

class ChordsFinder {

    private val chordPattern = Regex("[A-G](?:#|b|Cb|Db|Eb|Fb|Gb|Ab|Bb)?(?:m|maj|maj7|sus4|sus2|dim|aug|add9|6|7|9|11|13)?(?:m|maj|maj7|sus4|sus2|dim|aug|add9|6|7|9|11|13)?(?:/[A-G](?:#|b|Db|Eb|Fb|Gb|Ab|Bb|Cb)?)?(?:\\([^)]*\\))?")


    fun findChords(chordsLines: List<String>): ArrayList<String> {
        val chords = arrayListOf<String>()


        for (chordLine in chordsLines) {
            val matches = chordPattern.findAll(chordLine)

            for (match in matches) {
                val matchStart = match.range.first
                val matchEnd = match.range.last
                val lineLength = chordLine.length


                val isSingleChordLine = matchEnd - matchStart == lineLength - 1

                if (isSingleChordLine || (matchStart == 0 && chordLine.getOrNull(matchEnd + 1)?.isWhitespace() == true) ||
                    (matchEnd == lineLength - 1 && chordLine.getOrNull(matchStart - 1)?.isWhitespace() == true) ||
                    (matchStart > 0 && matchEnd < lineLength - 1 &&
                            chordLine.getOrNull(matchStart - 1)?.isWhitespace() == true &&
                            chordLine.getOrNull(matchEnd + 1)?.isWhitespace() == true)
                ) {
                    if (!chords.contains(match.value)) {
                        chords.add(match.value)
                    }
                }
            }
        }
        return chords
    }

    fun isItAChordsLine(songLine: String): Boolean {


        val matches = chordPattern.findAll(songLine)

        for (match in matches) {
            val matchStart = match.range.first
            val matchEnd = match.range.last
            val lineLength = songLine.length


            val isSingleChordLine = matchEnd - matchStart == lineLength - 1

            //only one chord in the line
            if (isSingleChordLine ||

                //chord is at the beginning of the line
                (matchStart == 0 && songLine.getOrNull(matchEnd + 1)?.isWhitespace() == true) ||

                //chord is at the end of the line
                (matchEnd == lineLength - 1 && songLine.getOrNull(matchStart - 1)?.isWhitespace() == true) ||

                //chord is in the middle of a line
                (matchStart > 0 && matchEnd < lineLength - 1 &&
                        songLine.getOrNull(matchStart - 1)?.isWhitespace() == true &&
                        songLine.getOrNull(matchEnd + 1)?.isWhitespace() == true)
            ) {

                return true
                }
            }

        return false
    }


}