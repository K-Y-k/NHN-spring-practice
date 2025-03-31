package org.example.customcode.farewell;

import org.example.framework.annotations.Part;

@Part
public class KoreanFarewell implements Farewell {

    public void sayGoodBye() {
        System.out.println("잘 가");
    }
}
