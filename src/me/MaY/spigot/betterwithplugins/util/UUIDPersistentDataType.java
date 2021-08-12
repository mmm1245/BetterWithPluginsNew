package me.MaY.spigot.betterwithplugins.util;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;


public class UUIDPersistentDataType implements PersistentDataType<byte[], UUID> {
	private static UUIDPersistentDataType INSTANCE = new UUIDPersistentDataType();

    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<UUID> getComplexType() {
        return UUID.class;
    }

    @Override
    public byte[] toPrimitive(UUID complex, PersistentDataAdapterContext context) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(complex.getMostSignificantBits());
        bb.putLong(complex.getLeastSignificantBits());
        return bb.array();
    }

    @Override
    public UUID fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        ByteBuffer bb = ByteBuffer.wrap(primitive);
        return new UUID(bb.getLong(), bb.getLong());
    }
    
    public static UUIDPersistentDataType getINSTANCE() {
    	return INSTANCE;
    }
}