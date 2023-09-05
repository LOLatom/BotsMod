package com.thefreak.botsmod.objects.items.bewlr;

import com.thefreak.botsmod.objects.items.bewlr.models.BanhirHeadModel;
import com.thefreak.botsmod.objects.items.loreandclueitems.BanhirHead;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class BanhirHeadGeoBEWLR extends GeoItemRenderer<BanhirHead> {
    public BanhirHeadGeoBEWLR() {
        super(new BanhirHeadModel());
    }


}
