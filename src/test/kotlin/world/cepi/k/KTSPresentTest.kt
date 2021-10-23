package world.cepi.k

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import javax.script.ScriptEngineManager

class KTSPresentTest : StringSpec({

    "kts is present" {
        ScriptEngineManager().getEngineByExtension("kts").shouldNotBeNull()
    }

})