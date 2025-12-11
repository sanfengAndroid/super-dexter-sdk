# super-dexter-sdk

![License](https://img.shields.io/badge/License-Apache2-blue)

[English](README.md) | [简体中文](README_CN.md)

`super-dexter-sdk` 是 [super-dexter](https://github.com/sanfengAndroid/super-dexter) 项目的核心 `Android SDK`，提供自定义修改 `Android DEX` 的配置能力。它将常规的  
`DEX` → `Smali` → `修改 Smali` → `重新打包 DEX`  
的复杂流程，简化为  
`代码开发编译生成新 DEX` → `新 DEX + 旧 DEX 合并生成最终修改后的 DEX`  
的自动化步骤，大幅提升 `DEX` 修改的效率与便利性。

### 传统 Smali 方式修改的局限性

传统通过 `Smali` 文件直接修改 DEX 的方式存在诸多问题：

- **易出错**：手动编辑 `Smali` 文件结构复杂、语法严格，错误修改容易导致回编译失败。
- **耗时高**：`Smali` 的重新编译过程冗长，修改与调试周期较慢，不利于快速迭代。
- **系统兼容性差**：由于 Smali 文件对大小写敏感，而部分操作系统大小写不敏感，极易引发文件名冲突。
- **学习成本高**：理解寄存器分配和控制流对新手极不友好。

### `super-dexter-sdk` 的改进

`super-dexter-sdk` 提供面向开发者的友好 `SDK`，无需手动编写 `Smali`，即可通过 `Java` 代码实现 `DEX` 层级修改。  
同时编写的代码能充分利用 **Android 官方编译器** 的优化能力，生成更优的代码，有效避免人工修改 `Smali` 时的语法与寄存器问题。

借助该机制，开发者可在熟悉的 `Android` 开发环境中完成 `DEX` 修改工作，既保留底层灵活性，又显著提升开发效率与稳定性。
