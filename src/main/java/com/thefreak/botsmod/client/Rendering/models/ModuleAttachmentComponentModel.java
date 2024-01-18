package com.thefreak.botsmod.client.Rendering.models;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ModuleAttachmentComponentModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "moduleattachmentcomponent"), "main");
	private final ModelPart ModuleAttachmentComponent;

	public ModuleAttachmentComponentModel(ModelPart root) {
		this.ModuleAttachmentComponent = root.getChild("ModuleAttachmentComponent");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition ModuleAttachmentComponent = partdefinition.addOrReplaceChild("ModuleAttachmentComponent", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 6.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.1F))
		.texOffs(0, 6).addBox(-6.1F, 3.0F, -1.0F, 3.0F, 7.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	public void copyFromArm(ModelPart arm) {
		this.ModuleAttachmentComponent.x = arm.x;
		this.ModuleAttachmentComponent.y = arm.y;
		this.ModuleAttachmentComponent.z = arm.z;
		this.ModuleAttachmentComponent.xRot = arm.xRot;
		this.ModuleAttachmentComponent.yRot = arm.yRot;
		this.ModuleAttachmentComponent.zRot = arm.zRot;

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		ModuleAttachmentComponent.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}