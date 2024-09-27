//package com.tpfinalprogramacion2.scenes.dependencies;
//
//import com.tpfinalprogramacion2.models.abilities.Ability;
//import com.tpfinalprogramacion2.models.characters.Enemy;
//import com.tpfinalprogramacion2.models.characters.Player;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.StackPane;
//
//public class PlayerService {
//
//    private static Ability selectedAbility;
//
//    // Realiza un ataque básico con la primera habilidad del jugador
//    public static void performBasicAttack(Player player, Enemy enemy, ProgressBar healthBar) {
//        Ability basicAttack = player.getAbility(0);
//
//        if (basicAttack != null) {
//            applyAbility(basicAttack, player, enemy, healthBar, null);
//        } else {
//            System.out.println("Player does not have an ability assigned.");
//        }
//    }
//
//    // Maneja el evento cuando se usa una habilidad en función del botón presionado
//    public static void onAbilityUse(MouseEvent event, Player player, Enemy enemy, ProgressBar healthBar, ImageView abilityImage) {
//        int abilityIndex = getAbilityIndexFromButton((StackPane) event.getSource());
//
//        // Selecciona la habilidad según el botón presionado
//        selectedAbility = player.getAbility(abilityIndex);
//        if (selectedAbility != null) {
//            applyAbility(selectedAbility, player, enemy, healthBar, abilityImage);
//
//            // Actualiza la interfaz del botón y su estado de cooldown
//            ButtonService.updateButtonState((StackPane) event.getSource(), selectedAbility.getCoolDownTime());
//        } else {
//            System.out.println("No ability found for the selected button.");
//        }
//    }
//
//    // Aplica la habilidad seleccionada al enemigo y actualiza la barra de salud
//    private static void applyAbility(Ability ability, Player player, Enemy enemy, ProgressBar healthBar, ImageView abilityImage) {
//        if (ability instanceof AttackAbility) {
//            System.out.println("Using attack ability: " + ability.getName());
//            double damage = ability.use(enemy, abilityImage);
//            System.out.println("Damage dealt: " + damage);
//
//            // Actualiza la barra de salud del enemigo
//            healthBar.setProgress(enemy.getHealth() / enemy.MAX_HEALTH);
//
//        } else if (ability instanceof BuffAbility) {
//            System.out.println("Using buff ability: " + ability.getName());
//            double buffValue = ability.use(player, abilityImage);
//            System.out.println("Buff applied: " + buffValue);
//
//            // Aquí podrías actualizar alguna barra de estado del jugador si es necesario
//        }
//    }
//
//    // Devuelve el índice de la habilidad según el botón que fue presionado
//    private static int getAbilityIndexFromButton(StackPane stackPane) {
//        return switch (stackPane.getId()) {
//            case "button_1" -> 1;
//            case "button_2" -> 2;
//            case "button_3" -> 3;
//            case "button_4" -> 4;
//            default -> throw new IllegalStateException("Unexpected button ID: " + stackPane.getId());
//        };
//    }
//}
