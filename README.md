# StonecutterDamage

**Plugin Description**

Receive damage if you step on stonecutter.

**Features**

- Take ~4 points of damage every you move on stonecutter
- Configurable damage and damage rate
- Configurable knockback
- Custom random death messages
- Permission to avoid damage

**Permissions**

- `stonecutterDamage.bypass` - avoid damage if you step on a stonecutter

**Settings**

```yaml
debug: false
damage:
  amount: 4.0
  rate: 2.0
knockback:
  enabled: true
  rate: 0.05
deathMessages:
  - <player> died stepping on stonecutter
  - <player> chop his leg
```

**Future plans**

- Give damage to every entity (Villagers, enemies, mobs, etc)
