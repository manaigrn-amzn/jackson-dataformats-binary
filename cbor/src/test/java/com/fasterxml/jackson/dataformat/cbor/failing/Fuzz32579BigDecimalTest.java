package com.fasterxml.jackson.dataformat.cbor.failing;

import java.math.BigInteger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORTestBase;

public class Fuzz32579BigDecimalTest extends CBORTestBase
{
    private final ObjectMapper MAPPER = cborMapper();

    // [dataformats-binary#267]
    public void testBigDecimalOverflow() throws Exception
    {
//        final byte[] input = readResource("/data/clusterfuzz-cbor-32579.cbor");
//        for (int i = 0; i < input.length; ++i) {
//            System.out.printf("%02X: %02X\n", i, input[i] & 0xFF);
//        }

        final byte[] input = new byte[] {
                (byte) 0xC4, // Tag: decimal fraction
                (byte) 0x82,
                0x1B,
                0x00, 0x00, 0x00, 0x00,
                0x7F,
                (byte) 0xFF,
                (byte) 0xFF,
                (byte) 0xFF,
                0x1B,
                (byte) 0xC4,
                (byte) 0x82,
                0x1B,
                0x2C,
                0x25,
                (byte) 0xFF,
                (byte) 0xF6,
                0x28,

        };
        
        
        JsonNode root = MAPPER.readTree(input);
        assertTrue(root.isNumber());
        assertTrue(root.isBigInteger());
        assertEquals(BigInteger.ZERO, root.bigIntegerValue());
    }
}
