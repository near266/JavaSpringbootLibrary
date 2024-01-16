package com.example.LibraryJavaBe.BookService.InterfaceSvc;

import com.example.LibraryJavaBe.BookService.Entities.Event;
import com.example.LibraryJavaBe.BookService.Entities.HandmadeItem;

public interface IEventOrHandmadeSvc {
    Event Add(Event event);
    HandmadeItem Addhandmade(HandmadeItem handmadeItem);
}
