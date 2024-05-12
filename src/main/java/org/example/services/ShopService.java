package org.example.services;

import org.example.models.Shop;
import org.example.repositories.ItemRepository;
import org.example.repositories.ShopRepository;

public class ShopService {

    private ShopRepository shopRepository;
    private ItemRepository itemRepository;

    public ShopService() {
        this.shopRepository = new ShopRepository();
        this.itemRepository = new ItemRepository();
    }

    public Shop getShop(int idShop) {
        return shopRepository.get(idShop);
    }

    public void buyItem(int idPlayer, int idItem) {
        itemRepository.updateItemQuantityOnBuy(idItem);
        itemRepository.updateItemIsBought(idItem);
        shopRepository.buyItem(idPlayer, idItem);

    }

    public void sellItem(int idPlayer, int idItem) {
        itemRepository.updateItemQuantityOnSell(idItem);
        shopRepository.sellItem(idPlayer, idItem);
    }

    public int countItemsBought(int idPlayer) {
        return itemRepository.countBoughtItems(idPlayer);
    }
}
