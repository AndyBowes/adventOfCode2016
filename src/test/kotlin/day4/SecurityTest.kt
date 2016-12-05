package day4

import io.kotlintest.specs.FeatureSpec
import java.util.stream.Collectors

class SecurityTest : FeatureSpec() {

    init {

        feature("Check pattern matching works") {

            scenario("Extract details from aaaaa-bbb-z-y-x-123[abxyz]"){
                val matcher = doorcodeRegex.matcher("aaaaa-bbb-z-y-x-123[abxyz]")
                matcher.matches() shouldBe true
                matcher.groupCount() shouldBe 3
                matcher.group(1) shouldEqual  "aaaaa-bbb-z-y-x"
                matcher.group(2) shouldEqual "123"
                matcher.group(3) shouldEqual "abxyz"
            }
        }


        feature("Calculate Checksum"){

            scenario("Door Code : aaaaa-bbb-z-y-x-123[abxyz]"){
                val doorCode = DoorCode("aaaaa-bbb-z-y-x", 123, "abxyz")
                val checksum = doorCode.calculateChecksum()
                checksum shouldEqual "abxyz"
                doorCode.isValid() shouldBe true
            }

            scenario("Door Code : a-b-c-d-e-f-g-h-987[abcde]"){
                val doorCode = DoorCode("a-b-c-d-e-f-g-h", 987, "abcde")
                val checksum = doorCode.calculateChecksum()
                checksum shouldEqual "abcde"
                doorCode.isValid() shouldBe true
            }

            scenario("Door Code : not-a-real-room-404[oarel]"){
                val doorCode = DoorCode("not-a-real-room", 987, "oarel")
                val checksum = doorCode.calculateChecksum()
                checksum shouldEqual "oarel"
                doorCode.isValid() shouldBe true
            }

            scenario("Door Code : totally-real-room-200[decoy]"){
                val doorCode = DoorCode("not-a-real-room", 200, "decoy")
                doorCode.isValid() shouldBe false
            }
        }

        feature("Read Door Code file"){
            scenario("Check that we can read in all of the door codes"){
                val doorCodes = readDoorCodesFile("day4/doorCodes.txt")
                doorCodes.size shouldBe 974
                doorCodes.filter { it.isValid() }.size shouldBe 666
                println(doorCodes.filter { it.isValid() }.sumBy { it.sectorId })
            }

            scenario("Check that we can read in all of the door codes"){
                val doorCodes = readDoorCodesFile("day4/doorCodes.txt")
                doorCodes.filter { it.isValid() }.forEach {
                    println("Name: ${it.encryptedName.decrypt(it.sectorId)} Sector: ${it.sectorId}")
                }
            }
        }


        feature("Decryption"){
            scenario("Decrypt sample"){
                "qzmt-zixmtkozy-ivhz".decrypt(343) shouldEqual "very encrypted name"
            }
        }


    }



}

