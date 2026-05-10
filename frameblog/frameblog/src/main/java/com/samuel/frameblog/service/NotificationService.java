package com.samuel.frameblog.service;

import com.samuel.frameblog.model.Comment;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @EventListener
    public void notifyNewComment(Comment comment) {
        System.out.println("====================================");
        System.out.println("NOVA MENSAGEM RECEBIDA NA FILA SIMULADA");
        System.out.println("Comentário: " + comment.getContent());
        System.out.println("Post ID: " + comment.getPost().getId());
        System.out.println("Usuário: " + comment.getUser().getName());
        System.out.println("====================================");
    }
}