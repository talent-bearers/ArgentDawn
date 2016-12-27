package talent.bearers.slimefusion.common.block

import com.teamwizardry.librarianlib.common.base.block.BlockMod
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.World
import talent.bearers.slimefusion.common.block.base.EnumStringSerializable

/**
 * @author WireSegal
 * Created at 12:54 PM on 12/25/16.
 */
class BlockStone(name: String) : BlockMod(name, Material.ROCK, *EnumStoneVariant.getNamesFor(name)) {
    companion object {
        val TYPE: PropertyEnum<EnumStoneVariant> = PropertyEnum.create("type", EnumStoneVariant::class.java)
    }

    init {
        setHardness(1.5F)
        setResistance(10.0F)
        soundType = SoundType.STONE
    }

    override fun createBlockState() = BlockStateContainer(this, TYPE)
    override fun damageDropped(state: IBlockState) = state.getValue(TYPE).dropped
    override fun getPickBlock(state: IBlockState, target: RayTraceResult?, world: World?, pos: BlockPos?, player: EntityPlayer?)
            = ItemStack(this, 1, getMetaFromState(state))
    override fun getMetaFromState(state: IBlockState) = state.getValue(TYPE).ordinal
    override fun getStateFromMeta(meta: Int) = defaultState.withProperty(TYPE, EnumStoneVariant[meta])

    enum class EnumStoneVariant : EnumStringSerializable {
        SMOOTH(4), BRICK, CRACKED_BRICK, CARVED, COBBLE;

        val dropped: Int

        constructor(dropped: Int) {
            this.dropped = dropped
        }
        constructor() {
            dropped = ordinal
        }

        companion object {
            operator fun get(meta: Int) = EnumStoneVariant.values().getOrElse(meta) { SMOOTH }

            fun getNamesFor(prefix: String) = EnumStoneVariant.values()
                    .map { prefix + "_" + it.getName() }
                    .toTypedArray()
        }
    }
}
