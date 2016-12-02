package day2

import io.kotlintest.specs.FeatureSpec

class BathroomTest : FeatureSpec() {
    init {
        feature("Standard Key pad mapping") {

            scenario("test movements") {
                '1'.move(Direction.U) shouldBe '1'
                '1'.move(Direction.L) shouldBe '1'
                '1'.move(Direction.R) shouldBe '2'
                '1'.move(Direction.D) shouldBe '4'
            }

            scenario("test movements2") {
                '9'.move(Direction.U) shouldBe '6'
                '9'.move(Direction.D) shouldBe '9'
                '9'.move(Direction.L) shouldBe '8'
                '9'.move(Direction.R) shouldBe '9'
            }

            scenario("String sequence of moves together"){
                '9'.move(Direction.L)
                        .move(Direction.U)
                        .move(Direction.R)
                        .move(Direction.D)
                        .move(Direction.L) shouldBe '8'
            }

            scenario("test direction mapping"){
                'L'.toString().toDirection() shouldBe Direction.L
                'R'.toString().toDirection() shouldBe Direction.R
                'U'.toString().toDirection() shouldBe Direction.U
                'D'.toString().toDirection() shouldBe Direction.D
            }
        }

        feature("Process Line") {
            scenario("Initial Line") {
                "ULL".processLine('5') shouldBe '1'
                "RRDDD".processLine('1') shouldBe '9'
                "L".processLine('9') shouldBe '8'
                "LU".processLine('9') shouldBe '5'
                "LURDL".processLine('9') shouldBe '8'
                "UUUUD".processLine('8') shouldBe '5'
            }
        }


        feature("Advanced Key Pad"){
            scenario("Start at 1"){
                '1'.move(Direction.L, advancedKeyPad) shouldBe '1'
                '1'.move(Direction.U, advancedKeyPad) shouldBe '1'
                '1'.move(Direction.R, advancedKeyPad) shouldBe '1'
                '1'.move(Direction.D, advancedKeyPad) shouldBe '3'
            }
            scenario("Start at 2"){
                '2'.move(Direction.L, advancedKeyPad) shouldBe '2'
                '2'.move(Direction.U, advancedKeyPad) shouldBe '2'
                '2'.move(Direction.R, advancedKeyPad) shouldBe '3'
                '2'.move(Direction.D, advancedKeyPad) shouldBe '6'
            }
            scenario("Start at 3"){
                '3'.move(Direction.L, advancedKeyPad) shouldBe '2'
                '3'.move(Direction.U, advancedKeyPad) shouldBe '1'
                '3'.move(Direction.R, advancedKeyPad) shouldBe '4'
                '3'.move(Direction.D, advancedKeyPad) shouldBe '7'
            }
            scenario("Start at 4"){
                '4'.move(Direction.L, advancedKeyPad) shouldBe '3'
                '4'.move(Direction.U, advancedKeyPad) shouldBe '4'
                '4'.move(Direction.R, advancedKeyPad) shouldBe '4'
                '4'.move(Direction.D, advancedKeyPad) shouldBe '8'
            }
            scenario("Start at 5"){
                '5'.move(Direction.L, advancedKeyPad) shouldBe '5'
                '5'.move(Direction.U, advancedKeyPad) shouldBe '5'
                '5'.move(Direction.R, advancedKeyPad) shouldBe '6'
                '5'.move(Direction.D, advancedKeyPad) shouldBe '5'
            }
            scenario("Start at 6"){
                '6'.move(Direction.L, advancedKeyPad) shouldBe '5'
                '6'.move(Direction.U, advancedKeyPad) shouldBe '2'
                '6'.move(Direction.R, advancedKeyPad) shouldBe '7'
                '6'.move(Direction.D, advancedKeyPad) shouldBe 'A'
            }
            scenario("Start at 7"){
                '7'.move(Direction.L, advancedKeyPad) shouldBe '6'
                '7'.move(Direction.U, advancedKeyPad) shouldBe '3'
                '7'.move(Direction.R, advancedKeyPad) shouldBe '8'
                '7'.move(Direction.D, advancedKeyPad) shouldBe 'B'
            }
            scenario("Start at 8"){
                '8'.move(Direction.L, advancedKeyPad) shouldBe '7'
                '8'.move(Direction.U, advancedKeyPad) shouldBe '4'
                '8'.move(Direction.R, advancedKeyPad) shouldBe '9'
                '8'.move(Direction.D, advancedKeyPad) shouldBe 'C'
            }
            scenario("Start at 9"){
                '9'.move(Direction.L, advancedKeyPad) shouldBe '8'
                '9'.move(Direction.U, advancedKeyPad) shouldBe '9'
                '9'.move(Direction.R, advancedKeyPad) shouldBe '9'
                '9'.move(Direction.D, advancedKeyPad) shouldBe '9'
            }
            scenario("Start at A"){
                'A'.move(Direction.L, advancedKeyPad) shouldBe 'A'
                'A'.move(Direction.U, advancedKeyPad) shouldBe '6'
                'A'.move(Direction.R, advancedKeyPad) shouldBe 'B'
                'A'.move(Direction.D, advancedKeyPad) shouldBe 'A'
            }
            scenario("Start at B"){
                'B'.move(Direction.L, advancedKeyPad) shouldBe 'A'
                'B'.move(Direction.U, advancedKeyPad) shouldBe '7'
                'B'.move(Direction.R, advancedKeyPad) shouldBe 'C'
                'B'.move(Direction.D, advancedKeyPad) shouldBe 'D'
            }
            scenario("Start at C"){
                'C'.move(Direction.L, advancedKeyPad) shouldBe 'B'
                'C'.move(Direction.U, advancedKeyPad) shouldBe '8'
                'C'.move(Direction.R, advancedKeyPad) shouldBe 'C'
                'C'.move(Direction.D, advancedKeyPad) shouldBe 'C'
            }
            scenario("Start at D"){
                'D'.move(Direction.L, advancedKeyPad) shouldBe 'D'
                'D'.move(Direction.U, advancedKeyPad) shouldBe 'B'
                'D'.move(Direction.R, advancedKeyPad) shouldBe 'D'
                'D'.move(Direction.D, advancedKeyPad) shouldBe 'D'
            }
        }


        feature("Process Lines"){
            scenario("Sample Data"){
                listOf("ULL","RRDDD","LURDL","UUUUD").processLines()
            }

            scenario("Submission "){
                listOf("UDRLRRRUULUUDULRULUDRDRURLLDUUDURLUUUDRRRLUUDRUUDDDRRRLRURLLLDDDRDDRUDDULUULDDUDRUUUDLRLLRLDUDUUUUDLDULLLDRLRLRULDDDDDLULURUDURDDLLRDLUDRRULDURDDLUDLLRRUDRUDDDLLURULRDDDRDRRLLUUDDLLLLRLRUULRDRURRRLLLLDULDDLRRRRUDRDULLLDDRRRDLRLRRRLDRULDUDDLDLUULRDDULRDRURRURLDULRUUDUUURDRLDDDURLDURLDUDURRLLLLRDDLDRUURURRRRDRRDLUULLURRDLLLDLDUUUDRDRULULRULUUDDULDUURRLRLRRDULDULDRUUDLLUDLLLLUDDULDLLDLLURLLLRUDRDLRUDLULDLLLUDRLRLUDLDRDURDDULDURLLRRRDUUDLRDDRUUDLUURLDRRRRRLDDUUDRURUDLLLRRULLRLDRUURRRRRLRLLUDDRLUDRRDUDUUUDRUDULRRULRDRRRDDRLUUUDRLLURURRLLDUDRUURDLRURLLRDUDUUDLLLUULLRULRLDLRDDDU",
                        "DRRRDRUDRLDUUDLLLRLULLLUURLLRLDRLURDRDRDRLDUUULDRDDLDDDURURUDRUUURDRDURLRLUDRRRDURDRRRDULLRDRRLUUUURLRUULRRDUDDDDUURLDULUDLLLRULUDUURRDUULRRDDURLURRUDRDRLDLRLLULULURLRDLRRRUUURDDUUURDRDRUURUDLULDRDDULLLLLRLRLLUDDLULLUDDLRLRDLDULURDUDULRDDRLUDUUDUDRLLDRRLLDULLRLDURUDRLRRRDULUUUULRRLUDDDLDUUDULLUUURDRLLULRLDLLUUDLLUULUULUDLRRDDRLUUULDDRULDRLURUURDLURDDRULLLLDUDULUDURRDRLDDRRLRURLLRLLLLDURDLUULDLDDLULLLRDRRRDLLLUUDDDLDRRLUUUUUULDRULLLDUDLDLURLDUDULRRRULDLRRDRUUUUUURRDRUURLDDURDUURURULULLURLLLLUURDUDRRLRRLRLRRRRRULLDLLLRURRDULLDLLULLRDUULDUDUDULDURLRDLDRUUURLLDLLUUDURURUD",
                        "UDUUUUURUDLLLRRRDRDRUDDRLLDRRLDRLLUURRULUULULRLLRUDDRLDRLUURDUDLURUULLLULLRRRULRLURRDDULLULULRUDDDUURDRLUDUURRRRUUULLRULLLDLURUDLDDLLRRRULDLLUURDRRRDRDURURLRUDLDLURDDRLLLUUDRUULLDLLLLUUDRRURLDDUDULUDLDURDLURUURDUUUURDLLLRUUURDUUUDLDUDDLUDDUDUDUDLDUDUUULDULUURDDLRRRULLUDRRDLUDULDURUURULLLLUDDDLURURLRLRDLRULRLULURRLLRDUDUDRULLRULRUDLURUDLLDUDLRDRLRDURURRULLDDLRLDDRLRDRRDLRDDLLLLDUURRULLRLLDDLDLURLRLLDULRURRRRDULRLRURURRULULDUURRDLURRDDLDLLLRULRLLURLRLLDDLRUDDDULDLDLRLURRULRRLULUDLDUDUDDLLUURDDDLULURRULDRRDDDUUURLLDRDURUDRUDLLDRUD",
                        "ULRDULURRDDLULLDDLDDDRLDUURDLLDRRRDLLURDRUDDLDURUDRULRULRULULUULLLLDRLRLDRLLLLLRLRRLRLRRRDDULRRLUDLURLLRLLURDDRRDRUUUDLDLDRRRUDLRUDDRURRDUUUDUUULRLDDRDRDRULRLLDLDDLLRLUDLLLLUURLDLRUDRLRDRDRLRULRDDURRLRUDLRLRLDRUDURLRDLDULLUUULDRLRDDRDUDLLRUDDUDURRRRDLDURRUURDUULLDLRDUDDLUDDDRRRULRLULDRLDDRUURURLRRRURDURDRULLUUDURUDRDRLDLURDDDUDDURUDLRULULURRUULDRLDULRRRRDUULLRRRRLUDLRDDRLRUDLURRRDRDRLLLULLUULRDULRDLDUURRDULLRULRLRRURDDLDLLRUUDLRLDLRUUDLDDLLULDLUURRRLRDULRLRLDRLDUDURRRLLRUUDLUURRDLDDULDLULUUUUDRRULLLLLLUULDRULDLRUDDDRDRDDURUURLURRDLDDRUURULLULUUUDDLRDULDDLULDUDRU",
                        "LRLRLRLLLRRLUULDDUUUURDULLLRURLDLDRURRRUUDDDULURDRRDURLRLUDLLULDRULLRRRDUUDDRDRULLDDULLLUURDLRLRUURRRLRDLDUDLLRLLURLRLLLDDDULUDUDRDLRRLUDDLRDDURRDRDUUULLUURURLRRDUURLRDLLUDURLRDRLURUURDRLULLUUUURRDDULDDDRULURUULLUDDDDLRURDLLDRURDUDRRLRLDLRRDDRRDDRUDRDLUDDDLUDLUDLRUDDUDRUDLLRURDLRUULRUURULUURLRDULDLDLLRDRDUDDDULRLDDDRDUDDRRRLRRLLRRRUUURRLDLLDRRDLULUUURUDLULDULLLDLULRLRDLDDDDDDDLRDRDUDLDLRLUDRRDRRDRUURDUDLDDLUDDDDDDRUURURUURLURLDULUDDLDDLRUUUULRDRLUDLDDLLLRLLDRRULULRLRDURRRLDDRDDRLU").processLines()
            }

            scenario("Advanced Sample Data"){
                listOf("ULL","RRDDD","LURDL","UUUUD").processLines(keyPad = advancedKeyPad)
            }

            scenario("Advanced Submission"){
                listOf("UDRLRRRUULUUDULRULUDRDRURLLDUUDURLUUUDRRRLUUDRUUDDDRRRLRURLLLDDDRDDRUDDULUULDDUDRUUUDLRLLRLDUDUUUUDLDULLLDRLRLRULDDDDDLULURUDURDDLLRDLUDRRULDURDDLUDLLRRUDRUDDDLLURULRDDDRDRRLLUUDDLLLLRLRUULRDRURRRLLLLDULDDLRRRRUDRDULLLDDRRRDLRLRRRLDRULDUDDLDLUULRDDULRDRURRURLDULRUUDUUURDRLDDDURLDURLDUDURRLLLLRDDLDRUURURRRRDRRDLUULLURRDLLLDLDUUUDRDRULULRULUUDDULDUURRLRLRRDULDULDRUUDLLUDLLLLUDDULDLLDLLURLLLRUDRDLRUDLULDLLLUDRLRLUDLDRDURDDULDURLLRRRDUUDLRDDRUUDLUURLDRRRRRLDDUUDRURUDLLLRRULLRLDRUURRRRRLRLLUDDRLUDRRDUDUUUDRUDULRRULRDRRRDDRLUUUDRLLURURRLLDUDRUURDLRURLLRDUDUUDLLLUULLRULRLDLRDDDU",
                        "DRRRDRUDRLDUUDLLLRLULLLUURLLRLDRLURDRDRDRLDUUULDRDDLDDDURURUDRUUURDRDURLRLUDRRRDURDRRRDULLRDRRLUUUURLRUULRRDUDDDDUURLDULUDLLLRULUDUURRDUULRRDDURLURRUDRDRLDLRLLULULURLRDLRRRUUURDDUUURDRDRUURUDLULDRDDULLLLLRLRLLUDDLULLUDDLRLRDLDULURDUDULRDDRLUDUUDUDRLLDRRLLDULLRLDURUDRLRRRDULUUUULRRLUDDDLDUUDULLUUURDRLLULRLDLLUUDLLUULUULUDLRRDDRLUUULDDRULDRLURUURDLURDDRULLLLDUDULUDURRDRLDDRRLRURLLRLLLLDURDLUULDLDDLULLLRDRRRDLLLUUDDDLDRRLUUUUUULDRULLLDUDLDLURLDUDULRRRULDLRRDRUUUUUURRDRUURLDDURDUURURULULLURLLLLUURDUDRRLRRLRLRRRRRULLDLLLRURRDULLDLLULLRDUULDUDUDULDURLRDLDRUUURLLDLLUUDURURUD",
                        "UDUUUUURUDLLLRRRDRDRUDDRLLDRRLDRLLUURRULUULULRLLRUDDRLDRLUURDUDLURUULLLULLRRRULRLURRDDULLULULRUDDDUURDRLUDUURRRRUUULLRULLLDLURUDLDDLLRRRULDLLUURDRRRDRDURURLRUDLDLURDDRLLLUUDRUULLDLLLLUUDRRURLDDUDULUDLDURDLURUURDUUUURDLLLRUUURDUUUDLDUDDLUDDUDUDUDLDUDUUULDULUURDDLRRRULLUDRRDLUDULDURUURULLLLUDDDLURURLRLRDLRULRLULURRLLRDUDUDRULLRULRUDLURUDLLDUDLRDRLRDURURRULLDDLRLDDRLRDRRDLRDDLLLLDUURRULLRLLDDLDLURLRLLDULRURRRRDULRLRURURRULULDUURRDLURRDDLDLLLRULRLLURLRLLDDLRUDDDULDLDLRLURRULRRLULUDLDUDUDDLLUURDDDLULURRULDRRDDDUUURLLDRDURUDRUDLLDRUD",
                        "ULRDULURRDDLULLDDLDDDRLDUURDLLDRRRDLLURDRUDDLDURUDRULRULRULULUULLLLDRLRLDRLLLLLRLRRLRLRRRDDULRRLUDLURLLRLLURDDRRDRUUUDLDLDRRRUDLRUDDRURRDUUUDUUULRLDDRDRDRULRLLDLDDLLRLUDLLLLUURLDLRUDRLRDRDRLRULRDDURRLRUDLRLRLDRUDURLRDLDULLUUULDRLRDDRDUDLLRUDDUDURRRRDLDURRUURDUULLDLRDUDDLUDDDRRRULRLULDRLDDRUURURLRRRURDURDRULLUUDURUDRDRLDLURDDDUDDURUDLRULULURRUULDRLDULRRRRDUULLRRRRLUDLRDDRLRUDLURRRDRDRLLLULLUULRDULRDLDUURRDULLRULRLRRURDDLDLLRUUDLRLDLRUUDLDDLLULDLUURRRLRDULRLRLDRLDUDURRRLLRUUDLUURRDLDDULDLULUUUUDRRULLLLLLUULDRULDLRUDDDRDRDDURUURLURRDLDDRUURULLULUUUDDLRDULDDLULDUDRU",
                        "LRLRLRLLLRRLUULDDUUUURDULLLRURLDLDRURRRUUDDDULURDRRDURLRLUDLLULDRULLRRRDUUDDRDRULLDDULLLUURDLRLRUURRRLRDLDUDLLRLLURLRLLLDDDULUDUDRDLRRLUDDLRDDURRDRDUUULLUURURLRRDUURLRDLLUDURLRDRLURUURDRLULLUUUURRDDULDDDRULURUULLUDDDDLRURDLLDRURDUDRRLRLDLRRDDRRDDRUDRDLUDDDLUDLUDLRUDDUDRUDLLRURDLRUULRUURULUURLRDULDLDLLRDRDUDDDULRLDDDRDUDDRRRLRRLLRRRUUURRLDLLDRRDLULUUURUDLULDULLLDLULRLRDLDDDDDDDLRDRDUDLDLRLUDRRDRRDRUURDUDLDDLUDDDDDDRUURURUURLURLDULUDDLDDLRUUUULRDRLUDLDDLLLRLLDRRULULRLRDURRRLDDRDDRLU").processLines(keyPad = advancedKeyPad)
            }
        }
    }
}