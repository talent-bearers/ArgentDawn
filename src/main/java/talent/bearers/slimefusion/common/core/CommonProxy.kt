package talent.bearers.slimefusion.common.core

import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import talent.bearers.slimefusion.common.block.ModBlocks
import talent.bearers.slimefusion.common.items.ModItems

/**
 * @author WireSegal
 * Created at 10:17 PM on 12/23/16.
 */
open class CommonProxy {
    open fun pre(e: FMLPreInitializationEvent) {
        ModTab
        ModItems
        ModBlocks
    }

    open fun init(e: FMLInitializationEvent) {
        // NO-OP
    }

    open fun post(e: FMLPostInitializationEvent) {
        // NO-OP
    }
}
