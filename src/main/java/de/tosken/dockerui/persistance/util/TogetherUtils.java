package de.tosken.dockerui.persistance.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * dockerui
 * User: Sebastian
 * Date: 31.05.2018
 * Time: 11:12
 */
public final class TogetherUtils {
    private TogetherUtils() {
    }

    public static String generateRef() {
        return RandomStringUtils.randomAlphanumeric(6);
    }
}
