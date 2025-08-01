package com.github.wildtooth.knowledge.core;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class KnowledgeAddon extends LabyAddon<KnowledgeConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.logger().info("Successfully enabled {}!", this.addonInfo().getDisplayName());
  }

  @Override
  protected Class<KnowledgeConfiguration> configurationClass() {
    return KnowledgeConfiguration.class;
  }
}
