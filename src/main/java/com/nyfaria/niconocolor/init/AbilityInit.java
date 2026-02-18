package com.nyfaria.niconocolor.init;

import com.nyfaria.niconocolor.NicoNoColor;
import com.nyfaria.niconocolor.ability.BWMarker;
import dev._100media.hundredmediaabilities.init.HMAMarkerInit;
import dev._100media.hundredmediaabilities.marker.Marker;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AbilityInit {
    public static DeferredRegister<Marker> ABILITIES = DeferredRegister.create(HMAMarkerInit.MARKERS.getRegistryKey(), NicoNoColor.MODID);
    public static RegistryObject<Marker> BW_MARKER = ABILITIES.register("bw_marker", BWMarker::new);

}
