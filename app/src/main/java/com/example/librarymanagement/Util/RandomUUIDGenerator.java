package com.example.librarymanagement.Util;

import java.util.UUID;

public class RandomUUIDGenerator
{
    public static String generateRandomId()
    {
        return UUID.randomUUID().toString().replace("-","");
    }
}
