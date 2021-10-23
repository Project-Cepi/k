package world.cepi.k

import org.jetbrains.kotlin.cli.common.repl.KotlinJsr223JvmScriptEngineFactoryBase
import org.jetbrains.kotlin.cli.common.repl.ScriptArgsWithTypes
import org.jetbrains.kotlin.js.dce.InputResource.Companion.file
import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngine
import org.jetbrains.kotlin.script.jsr223.KotlinStandardJsr223ScriptTemplate
import world.cepi.kstom.Manager
import java.net.URLClassLoader
import java.io.File
import javax.script.Bindings
import javax.script.ScriptContext
import javax.script.ScriptEngine

internal class KTSFactory : KotlinJsr223JvmScriptEngineFactoryBase() {
    override fun getScriptEngine(): ScriptEngine {
        return KotlinJsr223JvmLocalScriptEngine(
                this,
                (Manager.extension.getExtension("kext")?.origin?.minestomExtensionClassLoader)
                    ?.urLs?.map { File(it.file) } ?: listOf(),
                KotlinStandardJsr223ScriptTemplate::class.java.canonicalName,
                { ctx, types ->
                    ScriptArgsWithTypes(
                        arrayOf<Any?>(ctx.getBindings(ScriptContext.ENGINE_SCOPE)),
                        types ?: emptyArray()
                    )
                },
                arrayOf(Bindings::class))
    }
}