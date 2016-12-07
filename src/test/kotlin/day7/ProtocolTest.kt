package day7

import day3.readResource
import io.kotlintest.specs.FeatureSpec
import java.util.stream.Collectors

class ProtocolTest : FeatureSpec() {
    init {
        feature("Check Protocols") {
            scenario("Check valid protocols are identified") {
                "abba[mnop]qrst".isProtocol() shouldBe true
            }
            scenario("should exclude abba is brackets") {
                "abcd[bddb]xyyx".isProtocol() shouldBe false
            }
            scenario("should exclude four characters all the same") {
                "aaaa[qwer]tyui".isProtocol() shouldBe false
            }
            scenario("should identify abba in longer strings") {
                "ioxxoj[asdfgh]zxcvbn".isProtocol() shouldBe true
            }
        }

        feature("Get Hypernets") {
            scenario("Single Hypernet") {
                val hypernets = "sacklxtvscuxwmhvtw[bahodhuctayhpnt]qicatycoooyspis".getHypernets()
                hypernets.size shouldBe 1
                hypernets[0] shouldEqual "bahodhuctayhpnt"
            }
            scenario("No hypernets") {
                val hypernets = "sacklxtvscuxwmhvtwbahodhuctayhpntqicatycoooyspis".getHypernets()
                hypernets.size shouldBe 0
            }
            scenario("Multiple hypernets") {
                val hypernets = "sac[klxtv]scuxwmhvtw[bahodhuctayhpnt]qica[tycoo]oyspis".getHypernets()
                hypernets.size shouldBe 3
                hypernets[0] shouldEqual "klxtv"
                hypernets[1] shouldEqual "bahodhuctayhpnt"
                hypernets[2] shouldEqual "tycoo"
            }
        }

//        aba[bab]xyz supports SSL (aba outside square brackets with corresponding bab within square brackets).
//                xyx[xyx]xyx does not support SSL (xyx, but no corresponding yxy).
//        aaa[kek]eke supports SSL (eke in supernet with corresponding kek in hypernet; the aaa sequence is not related, because the interior character must be different).
//        zazbz[bzb]cdb supports SSL (zaz has no corresponding aza, but zbz has a corresponding bzb, even though zaz and zbz overlap).


        feature("Find Supernets"){
            scenario("aba[bab]xyz"){
                val supernets = "aba[bab]xyz".getSupernets()
                supernets.size shouldBe 2
                supernets[0] shouldEqual "aba"
                supernets[1] shouldEqual "xyz"
            }
        }

        feature("Find Inverse"){
            scenario("aba"){
                "aba".inverse() shouldEqual "bab"
            }
        }

        feature("Submission") {
            scenario("Part 1") {
                val entries = readResource("day7/input.txt").collect(Collectors.toList<String>())
                println(entries.findProtocols().size)
            }

            scenario("Part 2") {
                val entries = readResource("day7/input.txt").collect(Collectors.toList<String>())
                println(entries.findSSLProtocols().size)
            }
        }

    }
}