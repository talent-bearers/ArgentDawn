package talent.bearers.slimefusion.common

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import talent.bearers.slimefusion.common.core.CommonProxy

/**
 * @author WireSegal
 * Created at 10:10 PM on 12/23/16.
 */
@Mod(modid = Slimefusion.MODID, name = Slimefusion.MODNAME, dependencies = Slimefusion.DEPENDENCIES, version = Slimefusion.VERSION)
class Slimefusion {
    companion object {
        const val MODID = "slimefusion"
        const val MODNAME = "Slimefusion"
        const val DEPENDENCIES = "required-after:librarianlib"
        const val VERSION = "0.0"
        const val PROXY_CLIENT = "talent.bearers.slimefusion.client.core.ClientProxy"
        const val PROXY_COMMON = "talent.bearers.slimefusion.common.core.CommonProxy"

        @SidedProxy(clientSide = PROXY_CLIENT, serverSide = PROXY_COMMON)
        lateinit var PROXY: CommonProxy
    }

    @Mod.EventHandler
    fun pre(e: FMLPreInitializationEvent) {
        PROXY.pre(e)
    }

    @Mod.EventHandler
    fun init(e: FMLInitializationEvent) {
        PROXY.init(e)
    }

    @Mod.EventHandler
    fun post(e: FMLPostInitializationEvent) {
        PROXY.post(e)
    }
}
