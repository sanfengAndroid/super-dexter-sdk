# super-dexter-sdk

![License](https://img.shields.io/badge/License-Apache2-blue)

[English](README.md) | [简体中文](README_CN.md)

`super-dexter-sdk` is the core `Android SDK` of the [super-dexter](https://github.com/sanfengAndroid/super-dexter) project, providing the capability to customize and modify `Android DEX` files. It simplifies the traditional complex workflow of  
`DEX` → `Smali` → `Modify Smali` → `Repackage DEX`  
into an automated process of  
`Code development and compilation to generate new DEX` → `Merge new DEX + old DEX to generate the final modified DEX`  
significantly improving the efficiency and convenience of `DEX` modification.

### Limitations of Traditional Smali-based Modification

Traditional DEX modification through direct `Smali` file editing has numerous issues:

- **Error-prone**: Manual editing of `Smali` files involves complex structure and strict syntax, making incorrect modifications likely to cause recompilation failures.
- **Time-consuming**: The recompilation process of `Smali` is lengthy, making modification and debugging cycles slow and unfavorable for rapid iteration.
- **Poor system compatibility**: Smali files are case-sensitive, while some operating systems are case-insensitive, easily causing filename conflicts.
- **High learning curve**: Understanding register allocation and control flow is extremely unfriendly to beginners.

### Improvements by `super-dexter-sdk`

`super-dexter-sdk` provides a developer-friendly `SDK` that enables `DEX`-level modifications through `Java` code without manually writing `Smali`.  
At the same time, the written code can fully leverage the optimization capabilities of the **official Android compiler** to generate better code, effectively avoiding syntax and register issues that occur when manually modifying `Smali`.

With this mechanism, developers can complete `DEX` modification work in a familiar `Android` development environment, retaining low-level flexibility while significantly improving development efficiency and stability.
