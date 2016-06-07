package com.wicpar.vulkan.tests;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.vulkan.VK10;
import org.lwjgl.vulkan.VkApplicationInfo;
import org.lwjgl.vulkan.VkInstance;
import org.lwjgl.vulkan.VkInstanceCreateInfo;

import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * @author Frederic Nieto
 * @since 07/06/2016.
 */
public class Main {

    public static Charset charset = Charset.forName("UTF-8");
    public static CharsetEncoder encoder = charset.newEncoder();
    public static PointerBuffer pp = MemoryUtil.memAllocPointer(1);

    public static void main(String[] args) throws CharacterCodingException {
        VkApplicationInfo info = VkApplicationInfo.create();
        info.sType(VK10.VK_STRUCTURE_TYPE_APPLICATION_INFO);
        info.pNext(0);
        info.pApplicationName(encoder.encode(CharBuffer.wrap("Vk Test\0")));
        info.pEngineName(encoder.encode(CharBuffer.wrap("Wicpar\0")));
        info.engineVersion(1);
        info.apiVersion(VK10.VK_API_VERSION_1_0);

        VkInstanceCreateInfo instanceInfo = VkInstanceCreateInfo.create();
        instanceInfo.sType(VK10.VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO);
        instanceInfo.pNext(0);
        instanceInfo.flags(0);
        instanceInfo.pApplicationInfo(info);
        instanceInfo.ppEnabledExtensionNames(null);
        instanceInfo.ppEnabledLayerNames(null);
        int n;
        if ((n = VK10.vkCreateInstance(instanceInfo, null, pp)) != VK10.VK_SUCCESS)
            throw new RuntimeException("failed to create VK instance "+ n);
        VkInstance instance = new VkInstance(pp.get(0), instanceInfo);

        System.out.println("lol");
        VK10.vkDestroyInstance(instance, null);

    }
}
