package com.example.springjpaalone.member.service;

import com.example.springjpaalone.member.entity.Item;
import com.example.springjpaalone.member.repository.ItemRepository;
import com.example.springjpaalone.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.saveItem(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findItem(Long id) {
        return itemRepository.findOne(id);
    }

}
