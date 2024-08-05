package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository //Repository 어노테이션안에 Component가 있으므로 컴포넌트 스캔의 대상이 됨
public class ItemRepository {

    //멀티쓰레드 환경에서는 HashMap을 사용하면 안됨 -> 여러 개의 쓰레드가 동시에 접근하므로
    //얘는 static에 싱글톤이다. 실무에서는 사용하고 싶으면 HashMap이 아닌 ConcurrentHashMap을 사용해야 한다.
    private static final Map<Long, Item> store = new HashMap<>();   //static 사용
    private static long sequence = 0L;  //static 사용

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);

        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);

        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();  //store에 있는 해시맵 데이터가 다 날라감
    }
}
