package cc.easyandroid.easyfiltermenu.simple;

import android.os.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/1/8.
 */
public class Text2 implements Serializable {
    /**
     * message : 处理成功
     * result : [{"fullPinyin":"nanshan","id":"741","latitude":22.545055,"longitude":113.932564,"name":"南山","subregions":[{"fullPinyin":"nanshan-shekou","id":"1247839","latitude":22.482307,"longitude":113.92022,"name":"蛇口"},{"fullPinyin":"nanshan-houhai","id":"1247833","latitude":22.522809,"longitude":113.946817,"name":"后海"},{"fullPinyin":"nanshan-nantou","id":"1247747","latitude":22.542046,"longitude":113.925284,"name":"南头"},{"fullPinyin":"nanshan-huaqiaocheng","id":"1247835","latitude":22.54056,"longitude":113.990657,"name":"华侨城"},{"fullPinyin":"nanshan-nanshanzhongxinqu","id":"1247831","latitude":22.526035,"longitude":113.944224,"name":"南山中心区"},{"fullPinyin":"nanshan-taoyuan","id":"1247757","name":"桃源"},{"fullPinyin":"nanshan-qianhai","id":"1247841","latitude":22.530257,"longitude":113.891176,"name":"前海"},{"fullPinyin":"nanshan-nanyou","id":"1247837","latitude":22.514934,"longitude":113.933403,"name":"南油"},{"fullPinyin":"nanshan-kejiyuan","id":"1247845","name":"科技园"},{"fullPinyin":"nanshan-xili","id":"1247843","latitude":22.586421,"longitude":113.95953,"name":"西丽"}]},{"fullPinyin":"futian","id":"1248135","latitude":22.5517306,"longitude":114.0555928,"name":"福田","subregions":[{"fullPinyin":"futian-huaqiangnan","id":"1247795","latitude":22.541701,"longitude":114.093504,"name":"华强南"},{"fullPinyin":"futian-huanggang","id":"1247807","latitude":22.524487,"longitude":114.080374,"name":"皇岗"},{"fullPinyin":"futian-yuanling","id":"1247797","latitude":22.559376,"longitude":114.109692,"name":"园岭"},{"fullPinyin":"futian-xinzhou","id":"1247801","latitude":22.532283,"longitude":114.053262,"name":"新洲"},{"fullPinyin":"futian-lianhua","id":"1247803","latitude":22.560122,"longitude":114.064025,"name":"莲花"},{"fullPinyin":"futian-huaqiang","id":"1247805","latitude":22.547904,"longitude":114.096325,"name":"华强"},{"fullPinyin":"futian-zhangcheng","id":"1247809","name":"长城"},{"fullPinyin":"futian-shangbu","id":"1247813","latitude":22.545115,"longitude":114.102196,"name":"上步"},{"fullPinyin":"futian-shisha","id":"1247815","latitude":22.529451,"longitude":114.060221,"name":"石厦"},{"fullPinyin":"futian-shangxiasha","id":"1247817","latitude":22.526256,"longitude":114.042829,"name":"上下沙"},{"fullPinyin":"futian-baoshuiqu","id":"1247819","latitude":22.514223,"longitude":114.062762,"name":"保税区"},{"fullPinyin":"futian-meilin","id":"1247823","latitude":22.56934,"longitude":114.047277,"name":"梅林"},{"fullPinyin":"futian-xiangmihushangquan","id":"1247825","latitude":22.548333,"longitude":114.041173,"name":"香蜜湖"},{"fullPinyin":"futian-bagualing","id":"1247827","latitude":22.565677,"longitude":114.105849,"name":"八卦岭"},{"fullPinyin":"futian-jingtian","id":"1247829","latitude":22.559377,"longitude":114.050436,"name":"景田"},{"fullPinyin":"futian-qita","id":"1248137","name":"其他"},{"fullPinyin":"futian-chegongmiaoa","id":"1272407","latitude":22.539208,"longitude":114.033899,"name":"车公庙"},{"fullPinyin":"futian-chegongmiao2","id":"1272422","name":"车公庙2"}]},{"fullPinyin":"longhuaxinqu","id":"1234573","latitude":22.702508,"longitude":114.051011,"name":"龙华新区","subregions":[{"fullPinyin":"longhuaxinqu-longhua","id":"1247765","latitude":22.676003,"longitude":114.037635,"name":"龙华"},{"fullPinyin":"longhuaxinqu-guanlan","id":"1247897","latitude":22.725884,"longitude":114.071843,"name":"观澜"},{"fullPinyin":"longhuaxinqu-minzhi","id":"1247899","latitude":22.623458,"longitude":114.047419,"name":"民治"},{"fullPinyin":"longhuaxinqu-dalang","id":"1247901","latitude":22.681447,"longitude":114.001965,"name":"大浪"},{"fullPinyin":"longhuaxinqu-qita","id":"1248123","name":"其他"}]},{"fullPinyin":"luohu","id":"742","latitude":22.581901,"longitude":114.150457,"name":"罗湖","subregions":[{"fullPinyin":"luohu-dongxiao","id":"1247731","latitude":22.584394,"longitude":114.144506,"name":"东晓"},{"fullPinyin":"luohu-datouling","id":"1247769","latitude":22.568259,"longitude":114.140903,"name":"大头岭"},{"fullPinyin":"luohu-sungang","id":"1247771","latitude":22.5715,"longitude":114.11945,"name":"笋岗"},{"fullPinyin":"luohu-honghu","id":"1247773","latitude":22.575174,"longitude":114.126331,"name":"洪湖"},{"fullPinyin":"luohu-cuizhu","id":"1247775","name":"翠竹"},{"fullPinyin":"luohu-buxin","id":"1247777","latitude":22.587239,"longitude":114.144521,"name":"布心"},{"fullPinyin":"luohu-shuiku","id":"1247779","name":"水库"},{"fullPinyin":"luohu-huangbeiling","id":"1247781","latitude":22.552417,"longitude":114.143367,"name":"黄贝岭"},{"fullPinyin":"luohu-caiwuwei","id":"1247783","latitude":22.54915,"longitude":114.112199,"name":"蔡屋围"},{"fullPinyin":"luohu-liantang","id":"1247785","latitude":22.566535,"longitude":114.180515,"name":"莲塘"},{"fullPinyin":"luohu-dongmen","id":"1247787","latitude":22.552365,"longitude":114.127939,"name":"东门"},{"fullPinyin":"luohu-renminnan","id":"1247789","latitude":22.54582,"longitude":114.12605,"name":"人民南"},{"fullPinyin":"luohu-nigang","id":"1247791","latitude":22.573028,"longitude":114.105302,"name":"泥岗"},{"fullPinyin":"luohu-yinhu","id":"1247793","latitude":22.574672,"longitude":114.096271,"name":"银湖"},{"fullPinyin":"luohu-qita","id":"1248115","name":"其他"}]},{"fullPinyin":"baoan","id":"745","latitude":22.580099,"longitude":113.89505,"name":"宝安","subregions":[{"fullPinyin":"baoan-songgang","id":"1247847","latitude":22.775675,"longitude":113.858085,"name":"松岗"},{"fullPinyin":"baoan-baoanzhongxinqu","id":"1247851","latitude":22.555597,"longitude":113.889133,"name":"宝安中心区"},{"fullPinyin":"baoan-taoyuanju","id":"1247853","latitude":22.62034,"longitude":113.867828,"name":"桃源居"},{"fullPinyin":"baoan-xinan","id":"1247855","latitude":22.565081,"longitude":113.916418,"name":"新安"},{"fullPinyin":"baoan-shiyan","id":"1247857","latitude":22.689397,"longitude":113.93537,"name":"石岩"},{"fullPinyin":"baoan-shajing","id":"1247859","latitude":22.744903,"longitude":113.81195,"name":"沙井"},{"fullPinyin":"baoan-fuyong","id":"1247861","latitude":22.67692,"longitude":113.834363,"name":"福永"},{"fullPinyin":"baoan-qita","id":"1248121","name":"其他"},{"fullPinyin":"baoan-xixianga","id":"1272410","name":"西乡"},{"fullPinyin":"baoan-guanhuia","id":"1272423","name":"莞惠"}]},{"fullPinyin":"longgang","id":"746","latitude":22.740383,"longitude":114.286712,"name":"龙岗","subregions":[{"fullPinyin":"longgang-longgangzhongxincheng","id":"1247863","latitude":22.739332,"longitude":114.23235,"name":"龙岗中心城"},{"fullPinyin":"longgang-henggang","id":"1247865","latitude":22.654411,"longitude":114.215408,"name":"横岗"},{"fullPinyin":"longgang-pingdi","id":"1247867","latitude":22.78107,"longitude":114.3217,"name":"坪地"},{"fullPinyin":"longgang-pinghu","id":"1247869","latitude":22.701687,"longitude":114.13263,"name":"平湖"},{"fullPinyin":"longgang-bujishangquan","id":"1247871","latitude":22.608064,"longitude":114.128021,"name":"布吉"},{"fullPinyin":"longgang-huizhou","id":"1247877","latitude":22.734851,"longitude":114.476032,"name":"惠州"},{"fullPinyin":"longgang-qita","id":"1248119","name":"其他"}]},{"fullPinyin":"yantiana","id":"1248133","latitude":22.6069813,"longitude":114.2784829,"name":"盐田","subregions":[{"fullPinyin":"yantiana-haishan","id":"1247879","latitude":22.566072,"longitude":114.246902,"name":"海山"},{"fullPinyin":"yantiana-qita","id":"1248139","name":"其他"},{"fullPinyin":"yantiana-shatoujiao","id":"1247885","latitude":22.557335,"longitude":114.237344,"name":"沙头角"}]},{"fullPinyin":"pingzhouquyu","id":"1272411","name":"坪洲区域","subregions":[{"fullPinyin":"pingzhouquyu-pingzhoushangquan","id":"1272412","name":"坪洲商圈"},{"fullPinyin":"pingzhouquyu-qita","id":"1272413","name":"其他"}]},{"fullPinyin":"ceshia","id":"1272414","name":"测试","subregions":[{"fullPinyin":"ceshia-qita","id":"1272424","name":"其他"}]},{"fullPinyin":"huiguiceshi","id":"1272415","name":"回归测试","subregions":[{"fullPinyin":"huiguiceshi-qita","id":"1272425","name":"其他"},{"fullPinyin":"huiguiceshi-ceshishangquan","id":"1272442","name":"测试商圈"},{"fullPinyin":"huiguiceshi-ceshishangquan0104","id":"1272443","name":"测试商圈0104"}]},{"fullPinyin":"tangyueceshi","id":"1272416","name":"唐月测试","subregions":[{"fullPinyin":"tangyueceshi-qita","id":"1272426","name":"其他"}]},{"fullPinyin":"xixiang1","id":"1272417","name":"西乡1","subregions":[{"fullPinyin":"xixiang1-33","id":"1272421","name":"33"},{"fullPinyin":"xixiang1-qita","id":"1272427","name":"其他"}]},{"fullPinyin":"tianlanqu","id":"1272428","name":"天蓝区","subregions":[{"fullPinyin":"tianlanqu-qita","id":"1272433","name":"其他"}]},{"fullPinyin":"ceAminchangqu","id":"1272429","name":"测A闵畅区","subregions":[{"fullPinyin":"ceAminchangqu-leguanshangquan","id":"1272430","name":"乐观商圈"},{"fullPinyin":"ceAminchangqu-qita","id":"1272434","name":"其他"}]},{"fullPinyin":"yuandanhouceshiquyu","id":"1272440","name":"元旦后测试区域","subregions":[{"fullPinyin":"yuandanhouceshiquyu-yuandanhouceshishangquan","id":"1272444","name":"元旦后测试商圈"},{"fullPinyin":"yuandanhouceshiquyu-qita","id":"1272448","name":"其他"},{"fullPinyin":"yuandanhouceshiquyu-yiyue1haoceshishangquanA","id":"1272456","name":"一月1号测试商圈A"}]},{"fullPinyin":"yiyuewuhaoceshiquyuA","id":"1272450","name":"一月五号测试区域A","subregions":[{"fullPinyin":"yiyuewuhaoceshiquyuA-qita","id":"1272453","name":"其他"},{"fullPinyin":"yiyuewuhaoceshiquyuA-yiyue1haoceshishangquanA","id":"1272456","name":"一月1号测试商圈A"}]},{"fullPinyin":"liaoceshi","id":"1272457","name":"廖测试","subregions":[{"fullPinyin":"liaoceshi-liaoceshishangquan","id":"1272459","name":"廖测试商圈"},{"fullPinyin":"liaoceshi-qita","id":"1272481","name":"其他"}]},{"fullPinyin":"yiyueqihaoceshiquyu","id":"1272460","name":"一月七号测试区域","subregions":[{"fullPinyin":"yiyueqihaoceshiquyu-yiyueqihaoceshishangquan","id":"1272462","name":"一月七号测试商圈"},{"fullPinyin":"yiyueqihaoceshiquyu-yiyueqihaoceshishangquan","id":"1272463","name":"一月七号测试商圈"},{"fullPinyin":"yiyueqihaoceshiquyu-qita","id":"1272482","name":"其他"}]}]
     * status : C0000
     */

    private String message;
    private String status;
    /**
     * fullPinyin : nanshan
     * id : 741
     * latitude : 22.545055
     * longitude : 113.932564
     * name : 南山
     * subregions : [{"fullPinyin":"nanshan-shekou","id":"1247839","latitude":22.482307,"longitude":113.92022,"name":"蛇口"},{"fullPinyin":"nanshan-houhai","id":"1247833","latitude":22.522809,"longitude":113.946817,"name":"后海"},{"fullPinyin":"nanshan-nantou","id":"1247747","latitude":22.542046,"longitude":113.925284,"name":"南头"},{"fullPinyin":"nanshan-huaqiaocheng","id":"1247835","latitude":22.54056,"longitude":113.990657,"name":"华侨城"},{"fullPinyin":"nanshan-nanshanzhongxinqu","id":"1247831","latitude":22.526035,"longitude":113.944224,"name":"南山中心区"},{"fullPinyin":"nanshan-taoyuan","id":"1247757","name":"桃源"},{"fullPinyin":"nanshan-qianhai","id":"1247841","latitude":22.530257,"longitude":113.891176,"name":"前海"},{"fullPinyin":"nanshan-nanyou","id":"1247837","latitude":22.514934,"longitude":113.933403,"name":"南油"},{"fullPinyin":"nanshan-kejiyuan","id":"1247845","name":"科技园"},{"fullPinyin":"nanshan-xili","id":"1247843","latitude":22.586421,"longitude":113.95953,"name":"西丽"}]
     */

    private ArrayList<Item2> result;

    public Text2() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResult(ArrayList<Item2> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Item2> getResult() {
        return result;
    }

    public static class ResultEntity implements Serializable  {


        private String fullPinyin;
        private String id;
        private double latitude;
        private double longitude;
        private String name;
        /**
         * fullPinyin : nanshan-shekou
         * id : 1247839
         * latitude : 22.482307
         * longitude : 113.92022
         * name : 蛇口
         */

        private ArrayList<Item2> subregions;

        public void setFullPinyin(String fullPinyin) {
            this.fullPinyin = fullPinyin;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSubregions(ArrayList<Item2> subregions) {
            this.subregions = subregions;
        }

        public String getFullPinyin() {
            return fullPinyin;
        }

        public String getId() {
            return id;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public String getName() {
            return name;
        }

        public ArrayList<Item2> getSubregions() {
            return subregions;
        }

//        @Override
//        public String getEasyItemTag() {
//            return name;
//        }

//        @Override
//        public HashMap<String, String> getEasyParameter() {
//            HashMap<String, String> map = new HashMap<>();
//            return map;
//        }

//        @Override
//        public EasyItemManager onCreatChildEasyItemManager() {
//            return new EasyItemManager(subregions);
//        }
        //        @Override
//        public boolean isNoLimitItem() {
//            return false;
//        }
//
//        @Override
//        public ArrayList<? extends IEasyItem> getChildItems() {
//            return subregions;
//        }




        public ResultEntity() {
        }

        protected ResultEntity(Parcel in) {
            this.fullPinyin = in.readString();
            this.id = in.readString();
            this.latitude = in.readDouble();
            this.longitude = in.readDouble();
            this.name = in.readString();
            this.subregions = new ArrayList<Item2>();
            in.readList(this.subregions, List.class.getClassLoader());
        }

    }
}
