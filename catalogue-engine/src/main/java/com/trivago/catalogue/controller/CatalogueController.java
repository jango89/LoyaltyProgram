package com.trivago.catalogue.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.trivago.catalogue.domain.Item;
import com.trivago.catalogue.json.AddItemDto;
import com.trivago.catalogue.json.UpdateItemDto;
import com.trivago.catalogue.usecase.AddItem;
import com.trivago.catalogue.usecase.FindAvailableItem;
import com.trivago.catalogue.usecase.FindAvailableItems;
import com.trivago.catalogue.usecase.UpdateItem;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalogue")
public class CatalogueController {

  private final FindAvailableItems findAvailableItems;
  private final AddItem addItem;
  private final UpdateItem updateItem;
  private final FindAvailableItem findAvailableItem;

  public CatalogueController(FindAvailableItems findAvailableItems,
      AddItem addItem, UpdateItem updateItem,
      FindAvailableItem findAvailableItem) {
    this.findAvailableItems = findAvailableItems;
    this.addItem = addItem;
    this.updateItem = updateItem;
    this.findAvailableItem = findAvailableItem;
  }

  @GetMapping
  @ResponseStatus(OK)
  public List<Item> findItems(
      @RequestParam(value = "page", defaultValue = "1", required = false) int page,
      @RequestParam(value = "limit", defaultValue = "10", required = false) int limit) {
    return findAvailableItems.execute(page, limit);
  }

  @GetMapping(value = "/{id}/{quantity}")
  @ResponseStatus(OK)
  public Item findItemAvailable(@PathVariable("id") String id,
      @PathVariable("quantity") Integer quantity) {
    return findAvailableItem.execute(id, quantity);
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public void addItem(@Valid @RequestBody AddItemDto addItemDto) {
    addItem.execute(addItemDto);
  }

  @PutMapping
  @ResponseStatus(ACCEPTED)
  public void update(@Valid @RequestBody UpdateItemDto updateItemDto) {
    updateItem.execute(updateItemDto);
  }
}
