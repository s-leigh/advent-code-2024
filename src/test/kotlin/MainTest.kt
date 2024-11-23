import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MainTest: FunSpec ( {
    test("maths") {
        1 + 2 shouldBe 3
    }
})