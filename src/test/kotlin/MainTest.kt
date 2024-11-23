import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MainTest: StringSpec ( {
    "maths" {
        1 + 2 shouldBe 3
    }
})
