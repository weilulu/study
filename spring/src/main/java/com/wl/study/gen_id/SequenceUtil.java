package com.wl.study.gen_id;

import java.util.Map;

/**
 * @Author:weilu
 * @Date: 2019/3/25 11:31
 */
public class SequenceUtil {

    private Map<String,Sequence> sequenceMap;
    private Sequence defaultSequence;

    public void setDefaultSequence(Sequence defaultSequence){
        this.defaultSequence = defaultSequence;
    }
    public void setSequenceMap(Map<String,Sequence> sequenceMap){
        this.sequenceMap = sequenceMap;
    }

    public long get(String name){
        Sequence sequence = null;
        if(sequenceMap != null && sequenceMap.size() > 0){
            sequence = sequenceMap.get(name);
        }
        if(sequence == null){
            if(defaultSequence != null){
                return defaultSequence.get(name);
            }else{
                throw new RuntimeException("sequence " + name + " undefined!");
            }
        }
        return sequence.get(name);
    }

}
