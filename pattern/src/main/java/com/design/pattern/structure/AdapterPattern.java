package com.design.pattern.structure;

/**
 * @Author:weilu
 * @Date: 2019/5/8 22:18
 * @Description: 适配器模式
 * 定义：将一个接口转换成客户希望的另一个接口，适配器模式使接口不兼容的那些类可以一起工作
 *
 * 模式角色：目标抽象类
 * 　       适配器类
 *          适配者类
 *          客户类
 */
public class AdapterPattern {

    /**
     * 普通播放器
     * 目示抽象类
     */
    interface MediaPlayer{
        void play(String audioType,String fileName);
    }

    /**
     * 高级播放器
     *
     */
    interface AdvancedMediaPlayer{
        void playVlc(String fileName);
        void playMp4(String fileName);
    }

    /**
     * 实现高级播放器
     */
    static class VlcPlayer implements AdvancedMediaPlayer{
        @Override
        public void playVlc(String fileName) {
            System.out.println(fileName);
        }

        @Override
        public void playMp4(String fileName) {
            //to do nothing
        }
    }

    /**
     * 实现高级播放器
     */
    static class Mp4Player implements AdvancedMediaPlayer{
        @Override
        public void playVlc(String fileName) {
            //to do nothing
        }

        @Override
        public void playMp4(String fileName) {
            System.out.println(fileName);
        }
    }

    /**
     * 适配器类
     * TODO 觉得适配器类可以不用实现MediaPlayer???
     * 如果后期，MediaPlayer需要支持其它的音频播放，那就要进行适配器与高级播放器的修改
     */
    static class MediaAdapter implements MediaPlayer{
        AdvancedMediaPlayer advancedMediaPlayer;
        public MediaAdapter(String audioType){

            //高级播放器这里可以改造成简单工厂
            if("vlc".equalsIgnoreCase(audioType)){
                advancedMediaPlayer = new VlcPlayer();
            }else if("mp4".equalsIgnoreCase(audioType)){
                advancedMediaPlayer = new Mp4Player();
            }
        }

        @Override
        public void play(String audioType, String fileName) {
            if("vlc".equalsIgnoreCase(audioType)){
                advancedMediaPlayer.playVlc(fileName);
            }else if("mp4".equalsIgnoreCase(audioType)){
                advancedMediaPlayer.playMp4(fileName);
            }
        }
    }

    /**
     * 变通播放类，
     * 因为持有了适配器，从只能播放mp3到可以播放vlc,mp4格式的音乐
     * 适配者类
     */
    static class AudioPlayer implements MediaPlayer{
        MediaAdapter mediaAdapter;

        @Override
        public void play(String audioType, String fileName) {
            if("mp3".equalsIgnoreCase(audioType)){
                System.out.println(fileName);
            }
            if("vlc".equalsIgnoreCase(audioType) || "mp4".equalsIgnoreCase(audioType)){
                mediaAdapter = new MediaAdapter(audioType);
                mediaAdapter.play(audioType,fileName);
            }
        }
    }

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3 playing...");
        audioPlayer.play("mp4", "alone.mp4 playing...");
        audioPlayer.play("vlc", "far far away.vlc playing...");
        audioPlayer.play("avi", "mind me.avi playing...");
    }

}
