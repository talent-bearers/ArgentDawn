package talent.bearers.slimefusion.common.block

import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import talent.bearers.slimefusion.common.block.base.BlockModPlanks
import talent.bearers.slimefusion.common.block.base.EnumStringSerializable
import talent.bearers.slimefusion.common.lib.LibNames

/**
 * @author WireSegal
 * Created at 10:01 AM on 12/25/16.
 */
class BlockPlanks : BlockModPlanks(LibNames.PLANKS, *VARIANTS) {
    companion object {
        val VARIANTS = arrayOf(
                "snow_planks",
                "cherry_planks",
                "cotton_planks",
                "ember_planks",
                "ebony_planks",
                "polar_planks",
                "shale_planks"
        )

        val TYPE: PropertyEnum<Type> = PropertyEnum.create("type", Type::class.java)
    }

    override fun damageDropped(state: IBlockState): Int {
        return getMetaFromState(state)
    }

    enum class Type : EnumStringSerializable {
        SNOW, CHERRY, COTTON, EMBER, EBONY, POLAR, SHALE
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, TYPE)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(TYPE).ordinal
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(TYPE, Type.values()[(meta and 7) % 7])
    }
}
