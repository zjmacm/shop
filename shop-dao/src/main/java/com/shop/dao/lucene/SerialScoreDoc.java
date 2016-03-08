package com.shop.dao.lucene;

import org.apache.lucene.search.ScoreDoc;

import java.io.Serializable;

/**
 * Created by yj on 14-12-11.
 * ScoreDoc 的wrapper类,用于写入redis 方便分页
 */
public class SerialScoreDoc implements Serializable{
    int doc;
    float score;
    int shardIndex;

    public ScoreDoc getScoreDoc(){
        return new ScoreDoc(this.doc, this.score, this.shardIndex);
    }

    public SerialScoreDoc setScoreDoc(ScoreDoc doc){
        this.doc = doc.doc;
        this.score = doc.score;
        this.shardIndex = doc.shardIndex;
        return this;
    }

    @Override
    public String toString() {
        return "SerialScoreDoc{" +
                "doc=" + doc +
                ", score=" + score +
                ", shardIndex=" + shardIndex +
                '}';
    }

    public int getDoc() {
        return doc;
    }

    public void setDoc(int doc) {
        this.doc = doc;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getShardIndex() {
        return shardIndex;
    }

    public void setShardIndex(int shardIndex) {
        this.shardIndex = shardIndex;
    }
}
