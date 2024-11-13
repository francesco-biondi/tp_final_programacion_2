package game.models.abilities;

import game.models.characters.Character;
import game.models.characters.Enemy;
import game.models.exceptions.AbilityNotAvailableException;
import game.scenes.dependencies.GameManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

    public class Nakama extends Ability {

        /**
         * Atributos
         */
        private String poster2;
        private ScheduledExecutorService autoAttack;

        /**
         * Getters y setters
         */
        public String getPoster2() {
            return poster2;
        }

        public void setPoster2(String poster2) {
            this.poster2 = poster2;
        }

        /**
         * Método principal para usar la habilidad
         */
        @Override
        public double use(Character character) {
            validateAbilityUsage(character);

            if (character instanceof Enemy enemy) {
                this.available = false;

                autoAttack = Executors.newScheduledThreadPool(1);
                autoAttack.scheduleAtFixedRate(() -> {
                    if (enemy.getHealth() > 0) {
                        enemy.setHealth(enemy.getHealth() - this.strength);
                    } else {
                        stopUse();
                    }
                }, 1, 2, TimeUnit.SECONDS);
                return strength;
            } else {
                throw new IllegalStateException("Character must be an enemy.");
            }
        }

        /**
         * Valida si la habilidad se puede usar
         */
        private void validateAbilityUsage(Character character) {
            if (!this.available) {
                throw new AbilityNotAvailableException("This ability is not available.");
            }
        }

        /**
         * Detiene el ataque automático y libera recursos
         */
        public void stopUse() {
            this.available = true;
            if (autoAttack != null && !autoAttack.isShutdown()) {
                autoAttack.shutdownNow();
            }
        }

        @Override
        public void upgrade() {
            super.upgrade();
        }

        @Override
        public void effect() {
        }
    }

