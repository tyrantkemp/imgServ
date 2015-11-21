/*package xz.com.dao;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
 
 
*//**
 * @ClassName: BaseMongoManager
 * @Description:
 * @author zc
 * @date 2014-5-8 涓嬪崍3:50:22
 * 
 *//*
public class Mongodb<E> {
    *//** 鏃ュ織瀵硅薄 *//*
    protected static final Logger logger = Logger
            .getLogger(Mongodb.class);
    @Autowired
    private MongoTemplate mongoTemplate;
 
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
 
    *//**
     * Entity鐨勭被鍨�
     *//*
    protected Class<E> entityClass;
 
    public Mongodb() {
        this.entityClass = Reflections.getSuperClassGenricType(getClass());
    }
 
    *//**
     * 
     * @Title: findAll
     * @Description: 鏌ヨ琛ㄦ牸鎵�鏈夌殑鏁版嵁
     * @return List<E> 褰撳墠瀹炰綋瀵瑰簲鐨勮〃鏍肩殑鎵�鏈夋暟鎹��
     *//*
    public List<E> findAll() {
        return this.findAll(null);
    }
 
    *//**
     * @Title: findAll
     * @Description: 鏌ヨ琛ㄦ牸鎵�鏈夌殑鏁版嵁
     * @param clazz
     *            璇ヨ〃鎵�瀵瑰簲鐨勫疄浣�
     * @param collcetionName
     *            琛ㄦ牸鍚嶇О
     * @return List<E>
     *//*
    public List<E> findAll(String collcetionName) {
 
        if (StringUtils.isBlank(collcetionName)) {
            collcetionName = mongoTemplate.getCollectionName(Reflections
                    .getSuperClassGenricType(getClass()));
            if (StringUtils.isBlank(collcetionName)) {
                collcetionName = this.entityClass.getSimpleName().toLowerCase();
            }
            logger.info("findAll's param collcetionName is null,so it default is "
                    + collcetionName);
        }
        return mongoTemplate.findAll(entityClass, collcetionName);
    }
 
    *//**
     * 
     * @Title: findCount
     * @Description:鏌ヨ褰撳墠瀹炰綋瀵瑰簲琛ㄦ牸鐨勬墍鏈夋暟鎹殑鏉℃暟
     * @return long 鎬绘潯鏁�
     * @throws
     *//*
    public long findCount() {
        long count = 0;
        count = mongoTemplate.count(new Query(), entityClass);
        return count;
    }
 
    *//***
     * 
     * @Title: insert
     * @Description: 鍏ュ簱
     * @param e
     *            瀹炰綋鏁版嵁
     * @param collectionName
     *            琛ㄥ悕
     *//*
    public void insert(E e, String collectionName) {
        mongoTemplate.insert(e, collectionName);
    }
 
    *//**
     * 
     * @Title: insert
     * @Description: 鍏ュ簱
     * @param e
     *            瀹炰綋鏁版嵁
     *//*
    public void insert(E e) {
        mongoTemplate.insert(e);
    }
 
    *//**
     * 鐢熶骇鏌ヨ璇彞
     * 
     * @Title: createCriteria
     * @Description: 鏍规嵁涓嶅悓鏉′欢鐢熶骇SQL
     * @param gtMap
     * @param ltMap
     * @param eqMap
     * @param gteMap
     * @param lteMap
     * @param regexMap
     * @param inMap
     * @param neMap
     * @return Criteria 鏌ヨ鐨勮鍙�
     * @throws
     *//*
    @SuppressWarnings("rawtypes")
    public Criteria createCriteria(Map<String, Object> gtMap,
            Map<String, Object> ltMap, Map<String, Object> eqMap,
            Map<String, Object> gteMap, Map<String, Object> lteMap,
            Map<String, String> regexMap, Map<String, Collection> inMap,
            Map<String, Object> neMap) {
        Criteria c = new Criteria();
        List<Criteria> listC= new ArrayList<Criteria>();
        Set<String> _set = null;
        if (gtMap != null && gtMap.size() > 0) {
            _set = gtMap.keySet();
            for (String _s : _set) {
                listC.add( Criteria.where(_s).gt(gtMap.get(_s)));
            }
        }
        if (ltMap != null && ltMap.size() > 0) {
            _set = ltMap.keySet();
            for (String _s : _set) {
                listC.add(  Criteria.where(_s).lt(ltMap.get(_s)));
            }
        }
        if (eqMap != null && eqMap.size() > 0) {
            _set = eqMap.keySet();
            for (String _s : _set) {
                listC.add(  Criteria.where(_s).is(eqMap.get(_s)));
            }
        }
        if (gteMap != null && gteMap.size() > 0) {
            _set = gteMap.keySet();
            for (String _s : _set) {
                listC.add( Criteria.where(_s).gte(gteMap.get(_s)));
            }
        }
        if (lteMap != null && lteMap.size() > 0) {
            _set = lteMap.keySet();
            for (String _s : _set) {
                listC.add(  Criteria.where(_s).lte(lteMap.get(_s)));
            }
        }
 
        if (regexMap != null && regexMap.size() > 0) {
            _set = regexMap.keySet();
            for (String _s : _set) {
                listC.add(  Criteria.where(_s).regex(regexMap.get(_s)));
            }
        }
 
        if (inMap != null && inMap.size() > 0) {
            _set = inMap.keySet();
            for (String _s : _set) {
                listC.add(  Criteria.where(_s).in(inMap.get(_s)));
            }
        }
        if (neMap != null && neMap.size() > 0) {
            _set = neMap.keySet();
            for (String _s : _set) {
                listC.add( Criteria.where(_s).ne(neMap.get(_s)));
            }
        }
        if(listC.size() > 0){
            Criteria [] cs = new Criteria[listC.size()];
            c.andOperator(listC.toArray(cs));
        }
        return c;
    }
 
    public Criteria createCriteria(Map<String, Object> eqMap) {
        return this.createCriteria(null, null, eqMap, null, null, null, null,
                null);
    }
 
    public Criteria createCriteria(Map<String, Object> eqMap,
            Map<String, Object> neMap) {
        return this.createCriteria(null, null, eqMap, null, null, null, null,
                neMap);
    }
 
    *//**
     * 
     * @Title: findCount
     * @Description: 鏍规嵁鍚勭鏉′欢鏌ヨ鎬绘暟
     * @param gtMap
     * @param ltMap
     * @param eqMap
     * @param gteMap
     * @param lteMap
     * @param regexMap
     * @param inMap
     * @param neMap
     * @return long 鎬绘暟
     * @throws
     *//*
    @SuppressWarnings("rawtypes")
    public long findCount(Map<String, Object> gtMap, Map<String, Object> ltMap,
            Map<String, Object> eqMap, Map<String, Object> gteMap,
            Map<String, Object> lteMap, Map<String, String> regexMap,
            Map<String, Collection> inMap, Map<String, Object> neMap) {
        long count = 0;
        Criteria c = this.createCriteria(gtMap, ltMap, eqMap, gteMap, lteMap,
                regexMap, inMap, neMap);
        Query query = null;
        if (c == null) {
            query = new Query();
        } else {
            query = new Query(c);
        }
        count = mongoTemplate.count(query, entityClass);
        return count;
    }
    *//***
     * 
    * @Title: findCount
    * @Description: 鏍规嵁鍒涘缓鐨勬潯浠舵煡璇㈡�绘暟
    * @param  queryC
    * @return long    杩斿洖绫诲瀷
    * @throws
     *//*
    public long findCount(Criteria queryC){
        Query query = new Query(queryC);
        return mongoTemplate.count(query, entityClass);
    }
 
    *//**
     * 
     * @Title: findCount
     * @Description: 鏍规嵁澶氫釜绉嶆潯浠� or 鐨勬柟寮忔煡璇�
     * @param orList
     *            or鐨勬煡璇㈡潯浠剁殑闆嗗悎
     * @return long
     * @throws
     *//*
    public long findCount(Criteria... orList) {
        long count = 0;
        Criteria c = new Criteria();
        Query query = null;
        if (orList != null && orList.length > 0) {
            c.orOperator(orList);
        }
        query = new Query(c);
 
        count = mongoTemplate.count(query, entityClass);
        return count;
    }
 
    @SuppressWarnings("rawtypes")
    public long findCount(Map<String, Object> gtMap, Map<String, Object> ltMap,
            Map<String, Object> eqMap, Map<String, String> regexMap,
            Map<String, Collection> inMap) {
        return this.findCount(gtMap, ltMap, eqMap, null, null, regexMap, inMap,
                null);
    }
 
    public long findCountByContainRegex(Map<String, Object> gtMap,
            Map<String, Object> ltMap, Map<String, Object> eqMap,
            Map<String, String> regexMap) {
        return this.findCount(gtMap, ltMap, eqMap, regexMap, null);
    }
 
     
    *//**
     * 
     * @Title: findListByPage
     * @Description: 鏍规嵁鍒嗛〉+鏉′欢鑾峰彇瀵瑰簲鐨勫疄浣撻泦鍚�
     * @param eqMap
     * @param gtMap
     * @param ltMap
     * @param gteMap
     * @param lteMap
     * @param regexMap
     * @param inMap
     * @param orders
     *            鎺掑簭闆嗗悎
     * @param pageIndex
     *            椤电爜
     * @param pageSize
     *            姣忛〉鏉℃暟
     * @return List<E> 瀹炰綋闆嗗悎
     * @throws
     *//*
    @SuppressWarnings("rawtypes")
    public List<E> findListByPage(Map<String, Object> eqMap,
            Map<String, Object> gtMap, Map<String, Object> ltMap,
            Map<String, Object> gteMap, Map<String, Object> lteMap,
            Map<String, String> regexMap, Map<String, Collection> inMap,
            Map<String, Object> neMap, List<Order> orders, int pageIndex,
            int pageSize) {
        List<E> list = null;
        Criteria c = this.createCriteria(gtMap, ltMap, eqMap, gteMap, lteMap,
                regexMap, inMap, neMap);
        Sort sort = null;
        if (orders != null && orders.size() > 0) {
            sort = new Sort(orders);
        }
        Query query = null;
        if (c == null) {
            query = new Query();
        } else {
            query = new Query(c);
        }
        if (sort != null) {
            query = query.with(sort);
        }
        if (pageSize > 0) {
            query.skip((pageIndex - 1) * pageSize);
            query.limit(pageSize);
        }
        list = mongoTemplate.find(query, entityClass);
 
        return list;
    }
    *//**
     * @Title: findOneObject
     * @Description: 绗﹀悎鏉′欢鐨勬煇涓�鏉℃暟鎹�
     * @param eqMap
     * @param gtMap
     * @param ltMap
     * @param gteMap
     * @param lteMap
     * @param regexMap
     * @param inMap
     * @return E 杩斿洖璇ユ暟鎹搴旂殑瀹炰綋
     *//*
    @SuppressWarnings("rawtypes")
    public E findObject(Map<String, Object> eqMap, Map<String, Object> gtMap,
            Map<String, Object> ltMap, Map<String, Object> gteMap,
            Map<String, Object> lteMap, Map<String, String> regexMap,
            Map<String, Collection> inMap) {
        E e = null;
        List<E> list = this.findList(eqMap, gtMap, ltMap, gteMap, lteMap,
                regexMap, inMap, null, null);
        if (list != null && list.size() > 0) {
            e = list.get(0);
        }
        return e;
    }
 
    *//**
     * 
     * @Title: findList
     * @Description: 澶氫釜鏌ヨ鏉′欢or鏂瑰紡缁勫悎鏌ヨ
     * @param orList
     * @return List<E>
     * @throws
     *//*
    public List<E> findList(Criteria... orList) {
        return this.findListByPage(null, 0, 0, orList);
    }
 
    *//**
     * 
     * @Title: findListByOrder
     * @Description: 澶氫釜鏌ヨ鏉′欢or鏂瑰紡缁勫悎鏌ヨ
     * @param orList
     * @param orders
     * @return List<E>
     * @throws
     *//*
    public List<E> findListByOrder(List<Order> orders, Criteria... orList) {
        return this.findListByPage(orders, 0, 0, orList);
    }
    *//**
     * 
    * @Title: findListByPage
    * @Description: 鏍规嵁鏌ヨ鏉′欢鐩存帴鏌ヨ
    * @param  c
    * @param  orders
    * @param  pageIndex
    * @param  pageSize
    * @return List<E>    
    * @throws
     *//*
    public List<E> findListByPage(Criteria c, List<Order> orders, int pageIndex,
            int pageSize){
        Query query = new Query(c);
        Sort sort = null;
        if (orders != null && orders.size() > 0) {
            sort = new Sort(orders);
        }
        if (sort != null) {
            query = query.with(sort);
        }
        if (pageSize > 0) {
            query.skip((pageIndex - 1) * pageSize);
            query.limit(pageSize);
        }
        return mongoTemplate.find(query, entityClass);
    }
    public List<E> findListByOrder(Criteria c, List<Order> orders){
        return this.findListByPage(c, orders, 0, 0);
    }
    public List<E> findList(Criteria c){
        return this.findListByPage(c, null, 0, 0);
    }
    *//**
     * 
    * @Title: findObject
    * @Description: 鏍规嵁鏌ヨ鏉′欢鏌ヨ鏌愪竴涓猳bject
    * @param  c
    * @return E    
    * @throws
     *//*
    public E findObject(Criteria c){
        List<E> list = this.findList(c);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
    *//**
     * 
     * @Title: findListByPage
     * @Description: 澶氫釜鏌ヨ鏉′欢or鏂瑰紡缁勫悎鏌ヨ
     * @param orList
     *            or鐨勬煡璇㈡潯浠剁殑闆嗗悎
     * @param orders鎺掑簭瑙勫垯
     * @param pageIndex
     *            绗嚑椤�
     * @param pageSize姣忛〉澶氬皯鏉�
     * @return List<E> 绗﹀悎鏉′欢鐨勯泦鍚�
     * @throws
     *//*
    public List<E> findListByPage(List<Order> orders, int pageIndex,
            int pageSize, Criteria... orList) {
        List<E> list = null;
        Criteria c = new Criteria();
        Query query = null;
        if (orList != null && orList.length > 0) {
            c.orOperator(orList);
        }
        query = new Query(c);
        Sort sort = null;
        if (orders != null && orders.size() > 0) {
            sort = new Sort(orders);
            query = query.with(sort);
        }
        if (pageSize > 0) {
            query.skip((pageIndex - 1) * pageSize);
            query.limit(pageSize);
        }
        list = mongoTemplate.find(query, entityClass);
        return list;
    }
 
    @SuppressWarnings("rawtypes")
    public List<E> findListNotContainOrder(Map<String, Object> eqMap,
            Map<String, Object> gtMap, Map<String, Object> ltMap,
            Map<String, Object> gteMap, Map<String, Object> lteMap,
            Map<String, String> regexMap, Map<String, Collection> inMap,
            Map<String, Object> neMap) {
        return this.findList(eqMap, gtMap, ltMap, gteMap, lteMap, regexMap,
                inMap, neMap, null);
    }
     
}*/